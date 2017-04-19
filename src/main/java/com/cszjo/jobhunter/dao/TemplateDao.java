package com.cszjo.jobhunter.dao;

import com.cszjo.jobhunter.model.Template;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Han on 2017/4/19.
 */
@Repository
public interface TemplateDao {

    List<Template> selectAll();

    int addTemplate(Template template);

    Template selectById(int id);

    int updateById(Template template);

    int deleteById(int id);
}
