package xyz.aqlabs.cookbookapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import xyz.aqlabs.cookbookapi.models.Cookbook;
import xyz.aqlabs.cookbookapi.models.input.CreateCookbookDto;
import xyz.aqlabs.cookbookapi.models.output.CookbookDto;
import xyz.aqlabs.cookbookapi.service.CookbookService;

import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class CookbookControllerTests {

    private MockMvc mockMvc;

    @Mock
    private CookbookService service;

    @InjectMocks
    private CookbookController controller;

    private ObjectMapper objectMapper;

    private CreateCookbookDto createDto;
    private Cookbook cookbook;
    private CookbookDto cookbookDto;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        createDto = new CreateCookbookDto(1, "Italian Cookbook", "Italian");
        cookbook = new Cookbook(1, 1, "Italian Cookbook", "Italian", "2025-12-06", "2025-12-06");
        cookbookDto = new CookbookDto();
        cookbookDto.setCookbookId(1);
        cookbookDto.setName("Italian Cookbook");
        cookbookDto.setEthnicity("Italian");
    }


    @Test
    void testCreateCookBook() throws Exception {
        when(service.createCookbook(createDto)).thenReturn(1);

        mockMvc.perform(post("/api/v1/cookbook")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createDto)))
                .andExpect(status().isOk())
                .andExpect(content().string("1"));

        verify(service, times(1)).createCookbook(createDto);
    }


    @Test
    void testUpdateCookbook() throws Exception {
        when(service.updateCookbook(cookbook)).thenReturn(1);

        mockMvc.perform(put("/api/v1/cookbook")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cookbook)))
                .andExpect(status().isOk())
                .andExpect(content().string("1"));

        verify(service, times(1)).updateCookbook(cookbook);
    }


    @Test
    void testDeleteCookBookByCookBookId() throws Exception {
        when(service.deleteCookbook(1)).thenReturn(1);

        mockMvc.perform(delete("/api/v1/cookbook")
                        .param("cookbookId", "1"))
                .andExpect(status().isOk())
                .andExpect(content().string("1"));

        verify(service, times(1)).deleteCookbook(1);
    }


    @Test
    void testGetCookBookByUserIdAndCookBookId() throws Exception {
        when(service.getCookbookByCookbookIdAndUserId(1, 1)).thenReturn(cookbookDto);

        mockMvc.perform(get("/api/v1/cookbook/single")
                        .param("userId", "1")
                        .param("cookbookId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cookbookId").value(1))
                .andExpect(jsonPath("$.name").value("Italian Cookbook"))
                .andExpect(jsonPath("$.ethnicity").value("Italian"));

        verify(service, times(1)).getCookbookByCookbookIdAndUserId(1, 1);
    }


    @Test
    void testGetListOfCookBookByUserId() throws Exception {
        when(service.getListOfCookbooksByUserId(1)).thenReturn(Collections.singletonList(cookbookDto));

        mockMvc.perform(get("/api/v1/cookbook/list")
                        .param("userId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].cookbookId").value(1))
                .andExpect(jsonPath("$[0].name").value("Italian Cookbook"))
                .andExpect(jsonPath("$[0].ethnicity").value("Italian"));

        verify(service, times(1)).getListOfCookbooksByUserId(1);
    }
}
