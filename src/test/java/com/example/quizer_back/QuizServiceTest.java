package com.example.quizer_back;

import com.example.quizer_back.Model.Category;
import com.example.quizer_back.Repository.CategoryRepository;
import com.example.quizer_back.Service.QuizService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class QuizServiceTest {

    @Mock
    CategoryRepository categoryRepository;
    List<Category> categories;

    QuizService quizService = new QuizService();

    @Before
    public void initTestData(){
        quizService.setCategoryRepository(categoryRepository);
        categories = new ArrayList<>();
        categories.add(new Category("Category"));
        setUpCategoryRepositoryMock();

    }

    public void setUpCategoryRepositoryMock(){
        when(categoryRepository.findAll()).thenReturn(categories);
        when(categoryRepository.findById(any())).thenAnswer(invocationOnMock ->{
            if((Integer)invocationOnMock.getArgument(0)>=categories.size()) throw new Exception();
            else {
                return Optional.ofNullable(categories.get(invocationOnMock.getArgument(0)));
            }
        } );
    }

    @Test
    public void testMock(){
        Assert.assertEquals(quizService.getCategoryList(),categories);
        Assert.assertEquals(quizService.getCategoryById(0),categories.get(0));

    }

}
