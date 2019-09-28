package com.ssrs.service.impl;

import com.ssrs.model.Artical;
import com.ssrs.mapper.ArticalMapper;
import com.ssrs.service.IArticalService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 文章信息表 服务实现类
 * </p>
 *
 * @author ssrs
 * @since 2019-09-28
 */
@Service("articalService")
public class ArticalServiceImpl extends ServiceImpl<ArticalMapper, Artical> implements IArticalService {

}
