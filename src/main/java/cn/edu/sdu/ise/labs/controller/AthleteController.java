package cn.edu.sdu.ise.labs.controller;

import cn.edu.sdu.ise.labs.dto.AthleteDTO;
import cn.edu.sdu.ise.labs.dto.AthleteQueryDTO;
import cn.edu.sdu.ise.labs.dto.AthleteUpdateDTO;
import cn.edu.sdu.ise.labs.model.ResultContext;
import cn.edu.sdu.ise.labs.service.AthleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 部门管理后端服务模块
 *
 * @author 李桢煜
 * @description
 * @date 2019/12/3 11:07
 */

@RestController
@RequestMapping("athlete")
public class AthleteController {
    @Autowired
    private AthleteService athleteService;


    @PostMapping("get")
    public ResultContext get(String athleteCode) {
        return ResultContext.returnSuccess(athleteService.getByCode(athleteCode));
    }

    @PostMapping("add")
    public ResultContext add(@RequestBody AthleteDTO athleteDTO) {
        return ResultContext.returnSuccess(athleteService.addAthlete(athleteDTO));
    }

    @PostMapping("update")
    public ResultContext update(@RequestBody AthleteUpdateDTO athleteDTO) {
        return ResultContext.returnSuccess(athleteService.updateAthlete(athleteDTO));
    }

    @PostMapping("list")
    public ResultContext list(@RequestBody AthleteQueryDTO queryDTO) {
        return ResultContext.returnSuccess(athleteService.listByPage(queryDTO));
    }


    @PostMapping("delete")
    public ResultContext delete(@RequestBody List<String> codeList) {
        Integer count = athleteService.deleteByCodes(codeList);
        return ResultContext.returnSuccess(count);
    }

    @PostMapping("importExcel")
    public ResultContext importExcel(@RequestParam(value = "Excel", required = false) MultipartFile excelFile) {
        Integer result = athleteService.addByExcel(excelFile);
        return ResultContext.returnSuccess(result);


    }
}
