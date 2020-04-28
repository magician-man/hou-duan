package cn.edu.sdu.ise.labs.service.utils;

import cn.edu.sdu.ise.labs.model.CompetitionEvent;
import cn.edu.sdu.ise.labs.vo.CompetitionEventVO;
import org.springframework.beans.BeanUtils;

/**
 * @author 李桢煜
 * @date 2020/3/15 17：58
 */
public class CompetitionEventUtils {
    /**
     * 将实体对象转换为VO对象
     *
     * @param competitionEvent 实体对象
     * @return VO对象
     */
    public static CompetitionEventVO convertToVO(CompetitionEvent competitionEvent) {
        CompetitionEventVO competitionEventVO = new CompetitionEventVO();
        BeanUtils.copyProperties(competitionEvent, competitionEventVO);
        return competitionEventVO;
    }
}
