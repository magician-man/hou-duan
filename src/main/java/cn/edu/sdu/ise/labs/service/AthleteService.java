package cn.edu.sdu.ise.labs.service;

import cn.edu.sdu.ise.labs.dto.AthleteDTO;
import cn.edu.sdu.ise.labs.dto.AthleteQueryDTO;
import cn.edu.sdu.ise.labs.dto.AthleteUpdateDTO;
import cn.edu.sdu.ise.labs.model.Page;
import cn.edu.sdu.ise.labs.vo.AthleteVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 运动员模块服务接口
 *
 * @author 李桢煜
 * @date 2020/3/11 23:37
 */
public interface AthleteService {
    /**
     * 获取运动员列表（分页）
     *
     * @param queryDTO
     * @return
     */
    Page<AthleteVO> listByPage(AthleteQueryDTO queryDTO);

    /**
     * 新建运动员
     *
     * @param athleteDTO 运动员输入对象
     * @return 运动员编码
     */
    String addAthlete(AthleteDTO athleteDTO);

    /**
     * 生成运动员编码
     *
     * @param prefix 前缀
     * @return 运动员编码
     */
    String generateAthleteCode(String prefix);

    /**
     * 更新运动员数据
     *
     * @param athleteUpdateDTO 运动员查询条件
     * @return 运动员编码
     */
    String updateAthlete(@Param("athleteUpdateDTO") AthleteUpdateDTO athleteUpdateDTO);

    /**
     * 根据编码列表删除运动员
     *
     * @param codeList 编码列表
     * @return 删除记录数
     */
    Integer deleteByCodes(List<String> codeList);

    /**
     * 根据运动员编码获取运动员详情
     *
     * @param athleteCode
     * @return
     */
    AthleteVO getByCode(String athleteCode);

    /**
     * 导入excel文件添加运动员
     *
     * @param excelFile
     * @return
     */
    Integer addByExcel(MultipartFile excelFile);
}
