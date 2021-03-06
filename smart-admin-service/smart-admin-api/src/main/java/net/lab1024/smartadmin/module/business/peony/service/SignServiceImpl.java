package net.lab1024.smartadmin.module.business.peony.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.BeanUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.module.business.peony.dao.SignDao;
import net.lab1024.smartadmin.module.business.peony.domain.dto.ActivityQueryDTO;
import net.lab1024.smartadmin.module.business.peony.domain.entity.ActivityEntity;
import net.lab1024.smartadmin.module.business.peony.domain.entity.SignEntity;
import net.lab1024.smartadmin.module.business.peony.domain.vo.ActivityVO;
import net.lab1024.smartadmin.module.business.peony.domain.vo.SignVO;
import net.lab1024.smartadmin.module.system.employee.domain.vo.EmployeeVO;
import net.lab1024.smartadmin.util.SmartBeanUtil;
import net.lab1024.smartadmin.util.SmartPageUtil;
import org.apache.commons.beanutils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * [ 活动 ]
 *
 * @author 莫京
 * @version 1.0
 * @company 华景城建筑设计有限公司(www.hjcadc.com)
 * @copyright (c)  华景城建筑设计有限公司( www.hjcadc.com )Inc. All rights reserved.
 * @date 2021-08-10 15:26:56
 * @since JDK1.8
 */
@Service
@Primary
public class SignServiceImpl extends ServiceImpl<SignDao, SignEntity> implements SignService {

    @Autowired
    private SignDao signDao;

    @Override
    public SignEntity selectByOpenidAndActivityid(String openid, String activityid) {
        QueryWrapper<SignEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.allEq(new HashMap<String, Object>() {{
            put("open_id", openid);
            put("activity_id", activityid);
        }});
        SignEntity signEntity = signDao.selectOne(queryWrapper);

        return signEntity;
    }

    @Override
    public ResponseDTO<IPage<SignVO>> getUserByActId(ActivityQueryDTO queryDTO) {
        QueryWrapper<SignEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("activity_id", queryDTO.getId());
        Page<SignEntity> page = SmartPageUtil.convert2QueryPage(queryDTO);
        IPage<SignEntity> signEntityPage = signDao.selectPage(page, wrapper);
        IPage<SignVO> signVOIPage = SmartBeanUtil.pageVoCovert(signEntityPage, SignVO.class);
        System.out.println("signVOIPage = " + signVOIPage);
        return ResponseDTO.succData(signVOIPage);
    }


}
