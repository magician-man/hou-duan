package cn.edu.sdu.ise.labs.service.impl;

import cn.edu.sdu.ise.labs.constant.PrefixConstant;
import cn.edu.sdu.ise.labs.dao.AthleteMapper;
import cn.edu.sdu.ise.labs.dao.CompetitionEventMapper;
import cn.edu.sdu.ise.labs.dao.TeamMapper;
import cn.edu.sdu.ise.labs.dto.AthleteDTO;
import cn.edu.sdu.ise.labs.dto.AthleteQueryDTO;
import cn.edu.sdu.ise.labs.dto.AthleteUpdateDTO;
import cn.edu.sdu.ise.labs.model.Athlete;
import cn.edu.sdu.ise.labs.model.Page;
import cn.edu.sdu.ise.labs.model.Token;
import cn.edu.sdu.ise.labs.service.AthleteService;
import cn.edu.sdu.ise.labs.utils.FormatUtils;
import cn.edu.sdu.ise.labs.utils.PageUtils;
import cn.edu.sdu.ise.labs.vo.AthleteVO;
import org.apache.ibatis.annotations.Param;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static cn.edu.sdu.ise.labs.utils.TokenContextHolder.getToken;

/**
 * @author 李桢煜
 * @Description
 * @date 2020/3/11
 */
@Service
public class AthleteServiceImpl implements AthleteService {
    @Autowired
    private AthleteMapper athleteMapper;
    @Autowired
    private CompetitionEventMapper competitionEventMapper;
    @Autowired
    private TeamMapper teamMapper;

    private final int INIT_VALUE = 1;
    private final int MAX_VALUE = 99999;
    private static int currentValue = 0;
    private String athleteCode;
    private int deleted;
    private Athlete athlete;
    @Autowired
    private AthleteService athleteService;

    /**
     * 查询运动员信息
     *
     * @param queryDTO 查询条件
     * @return 返回运动员实体对象
     */
    @Override
    public Page<AthleteVO> listByPage(AthleteQueryDTO queryDTO) {
        if (queryDTO == null) {
            queryDTO = new AthleteQueryDTO();
        }

        queryDTO.setName(FormatUtils.makeFuzzySearchTerm(queryDTO.getName()));
        queryDTO.setRegCode(FormatUtils.makeFuzzySearchTerm(queryDTO.getRegCode()));
        queryDTO.setIdNumber(FormatUtils.makeFuzzySearchTerm(queryDTO.getIdNumber()));


        Integer size = athleteMapper.count(queryDTO);
        PageUtils pageUtils = new PageUtils(queryDTO.getPage(), queryDTO.getPageSize(), size);
        Page<AthleteVO> pageData = new Page<>(pageUtils.getPage(), pageUtils.getPageSize(), pageUtils.getTotal(), new ArrayList<>());
        if (size == 0) {
            // 没有命中，则返回空数据。
            return pageData;
        }
        List<Athlete> list = athleteMapper.list(queryDTO, pageUtils.getOffset(), pageUtils.getLimit());
        for (Athlete athlete : list) {
            pageData.getList().add(convertToVO(athlete));
        }

        return pageData;
    }

    /**
     * 新建运动员
     *
     * @param athleteDTO 部门输入对象
     * @return 运动员编码
     */
    @Override
    public String addAthlete(AthleteDTO athleteDTO) {
        // 校验输入数据正确性
        validateAthlete(athleteDTO);
        // 创建实体对象，用以保存到数据库
        Athlete athlete = new Athlete();
        // 将输入的字段全部复制到实体对象中
        BeanUtils.copyProperties(athleteDTO, athlete);
        // 生成业务代码
        athlete.setAthleteCode(athleteService.generateAthleteCode(PrefixConstant.ATHLETE));
        // 将token相关信息填入athlete对象
        formatInsert(athlete);
        athlete.setCreatedAt(new Date());
        Assert.isTrue(athleteMapper.countAC(athlete.getAthleteCode()) == 0, "对不起，由于系统原因，运动员编码生成失败，请多试几次");
        // 调用DAO方法保存到数据库表
        if (athleteMapper.countAC(athlete.getAthleteCode()) == 0) {
            athleteMapper.insert(athlete);

            return athlete.getAthleteCode();
        }
        return null;

    }

    public void validateAthlete(AthleteDTO athleteDTO) {
        Assert.isTrue(athleteMapper.countRegCode(athleteDTO) == 0, "运动员注册号已存在");
        Assert.isTrue(athleteMapper.countCompetitionEventCode(athleteDTO) >= 1, "比赛项目不存在");
    }

    public void validateAthlete2(AthleteUpdateDTO athleteDTO) {
        Assert.isTrue(athleteMapper.countRegCode2(athleteDTO) == 0, "运动员注册号已存在");
        Assert.isTrue(athleteMapper.countCompetitionEventCode2(athleteDTO) >= 1, "比赛项目不存在");
    }

    private static void setFieldValue(Field field, Object o, String value) {
        if (field.getType() != String.class) {
            return;
        }
        boolean accessible = field.isAccessible();
        field.setAccessible(true);
        try {
            field.set(o, value);
        } catch (Exception ex) {
        }

        field.setAccessible(accessible);
    }

    /**
     * 设置数据
     *
     * @param model Athlete
     */
    public static void formatInsert(Object model) {
        Token token = getToken();
        Field field = ReflectionUtils.findField(model.getClass(), "createdBy");
        if (field != null) {
            setFieldValue(field, model, token.getTeacherCode());
        }

        field = ReflectionUtils.findField(model.getClass(), "updatedBy");
        if (field != null) {
            setFieldValue(field, model, token.getTeacherCode());
        }
    }

    /**
     * 生成各业务表唯一编码
     *
     * @param prefix 前缀
     * @return 唯一编码
     */
    @Override
    public String generateAthleteCode(String prefix) {
        if (currentValue > MAX_VALUE) {
            throw new RuntimeException("业务主键序数值超过了9999，请明天再来操作该业务");
        }
        SimpleDateFormat dateFormat = (new SimpleDateFormat("yyMMdd"));
        SimpleDateFormat dateFormat1 = (new SimpleDateFormat("HHmmSS"));
        String datePart = dateFormat.format(new Date());
        String datePart1 = dateFormat1.format(new Date());
        //athleteCode = String.format("%s%s%04d", prefix, datePart, currentValue);
        athleteCode = String.format("%s%s%s", prefix, datePart, datePart1);
        currentValue = currentValue + 1;
        return athleteCode;

    }


    /**
     * 更新运动员数据
     *
     * @param athleteUpdateDTO 运动员查询条件
     * @return 运动员编码
     */
    @Override
    public String updateAthlete(@Param("athleteUpdateDTO") AthleteUpdateDTO athleteUpdateDTO) {
        //AthleteUtils.validateAthlete(athleteDTO);
        Token token = getToken();
        Assert.hasText(athleteUpdateDTO.getAthleteCode(), "运动员编码不能为空");
        validateAthlete2(athleteUpdateDTO);
        Athlete athlete = athleteMapper.getByCode(athleteUpdateDTO.getAthleteCode());
        Assert.notNull(athlete, "未找到运动员，编码为：" + athleteCode);

        BeanUtils.copyProperties(athleteUpdateDTO, athlete);
        //athlete.setUpdatedBy(athlete.getAthleteCode());
        athlete.setUpdatedBy(token.getTeacherCode());
        athlete.setUpdatedAt(new Date());
        athleteMapper.updateByPrimaryKey(athlete);
        return athlete.getAthleteCode();
    }

    /**
     * 根据编码列表，批量删除运动员
     *
     * @param codeList 编码列表
     */
    @Override
    public Integer deleteByCodes(List<String> codeList) {
        Assert.notEmpty(codeList, "运动员编码列表不能为空");
        deleted = athleteMapper.deleteByCodes(codeList);
        return deleted;
    }

    /**
     * 根据运动员编码获取运动员详情
     *
     * @param athleteCode
     * @return
     */
    @Override
    public AthleteVO getByCode(String athleteCode) {
        Assert.notNull(athleteCode, "运动员编码不能为空");
        athlete = athleteMapper.getByCode(athleteCode);
        Assert.notNull(athlete, "不存在此运动员");
//        return AthleteUtils.convertToVO(athlete);
        return convertToVO(athlete);
    }

    private AthleteVO convertToVO(Athlete athlete) {
        AthleteVO athleteVO = new AthleteVO();
        BeanUtils.copyProperties(athlete, athleteVO);
        athleteVO.setCompetitionEventName(competitionEventMapper.getByCode(athlete.getCompetitionEventCode()).getCompetitionEventName());
        athleteVO.setTeamName(teamMapper.getByCode(athlete.getTeamCode()).getTeamName());
        athleteVO.setUpdatedAt(FormatUtils.formatFullDate(athlete.getUpdatedAt()));
        athleteVO.setCreatedAt(FormatUtils.formatFullDate(athlete.getCreatedAt()));
        return athleteVO;
    }

    @Override
    public Integer addByExcel(MultipartFile excelFile) {
        Workbook workbook = null;
        //获取文件名字

        String fileName = excelFile.getOriginalFilename();
        //判断后缀
        if (fileName.endsWith("xls")) {
            try {
                workbook = new HSSFWorkbook(excelFile.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (fileName.endsWith("xlsx")) {

            try {
                workbook = new XSSFWorkbook(excelFile.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            return null;
        }
        //获取工作sheet
        Sheet sheet = workbook.getSheet("sheet1");
        //获取行数
        int rows = sheet.getLastRowNum();
        if (rows == 0) {
            return null;
        }
        int j = 0;
        for (int i = 1; i <= rows + 1; i++) {
            //读取当前行
            Row row = (Row) sheet.getRow(i);
            AthleteDTO athlete = new AthleteDTO();
            if (row != null) {
                //获取第0列
                //athlete.setAthleteCode(row.getCell(0).getStringCellValue());
                row.getCell(0).setCellType(CellType.STRING);
                row.getCell(1).setCellType(CellType.STRING);
                row.getCell(2).setCellType(CellType.STRING);
                row.getCell(4).setCellType(CellType.STRING);
                row.getCell(7).setCellType(CellType.STRING);
                //第一列 以此类推
                athlete.setRegCode(row.getCell(0).getStringCellValue());
                //将拿到的数据插入数据库 根据自己需要的类型转换
                athlete.setTeamCode(row.getCell(1).getStringCellValue());
                athlete.setName(row.getCell(2).getStringCellValue());
                athlete.setIdType((int) (row.getCell(3).getNumericCellValue()));
                athlete.setIdNumber(row.getCell(4).getStringCellValue());
                athlete.setGender((int) (row.getCell(5).getNumericCellValue()));
                athlete.setAge((int) (row.getCell(6).getNumericCellValue()));
                athlete.setCompetitionEventCode((row.getCell(7).getStringCellValue()));
                // athlete.setIdType((int) (row.getCell(8).getNumericCellValue()));
                // 生成业务代码
            /*    athlete.setAthleteCode(athleteService.generateAthleteCode(PrefixConstant.ATHLETE));
                // 将token相关信息填入athlete对象
                formatInsert(athlete);
                athlete.setCreatedAt(new Date());
                athlete.setUpdatedAt(new Date());
                // 调用DAO方法保存到数据库表
                athleteMapper.insert(athlete);  */

                String flag = addAthlete(athlete);
                if (flag != null) {
                    j = j + 1;
                }
            }
        }


        return j;
    }
}
