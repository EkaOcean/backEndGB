package lesson6;

import lesson6.db.dao.CategoriesMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class ExampleTestMain {
    public static void main(String[] args) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);

        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        CategoriesMapper categoriesMapper = sqlSession.getMapper(CategoriesMapper.class);
        lesson6.db.model.CategoriesExample categoriesExample = new lesson6.db.model.CategoriesExample();

        categoriesExample.createCriteria().andIdEqualTo(1);

//        List<Categories> list = categoriesMapper.selectByExample(categoriesExample);
//        System.out.println(categoriesMapper.countByExample(categoriesExample));

        lesson6.db.model.Categories selected = categoriesMapper.selectByPrimaryKey(2);
        System.out.println("ID: " + selected.getId() + "\ntitle: " + selected.getTitle());

        lesson6.db.model.Categories categories = new lesson6.db.model.Categories();
        //categories.setId(Long.valueOf(3));
        categories.setTitle("Test");
        categoriesMapper.insert(categories);
        sqlSession.commit();
    }

}