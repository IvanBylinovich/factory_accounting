package by.factory_accounting;

import by.factory_accounting.controller.HomeController;
import by.factory_accounting.entity.accounting.Operation;
import by.factory_accounting.entity.accounting.Product;
import by.factory_accounting.tool.ConverterDTO;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class FactoryAccountingApplicationTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ConverterDTO converterDTO;


    @Autowired
    private HomeController controller;

    @Test
    public void test() throws Exception{
        this.mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    private void converterTest(){

    }

    @Test
    void contextLoads() {
    }

}
