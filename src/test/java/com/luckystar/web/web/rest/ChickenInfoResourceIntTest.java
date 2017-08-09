package com.luckystar.web.web.rest;

import com.luckystar.web.LuckystarApp;

import com.luckystar.web.domain.ChickenInfo;
import com.luckystar.web.repository.ChickenInfoRepository;
import com.luckystar.web.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.luckystar.web.domain.enumeration.State;
/**
 * Test class for the ChickenInfoResource REST controller.
 *
 * @see ChickenInfoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = LuckystarApp.class)
public class ChickenInfoResourceIntTest {

    private static final String DEFAULT_USER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_USER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_NICK_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NICK_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_STAR_ID = 1;
    private static final Integer UPDATED_STAR_ID = 2;

    private static final LocalDate DEFAULT_REG_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_REG_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_COOKIE = "AAAAAAAAAA";
    private static final String UPDATED_COOKIE = "BBBBBBBBBB";

    private static final Double DEFAULT_TIME_RATE = 1D;
    private static final Double UPDATED_TIME_RATE = 2D;

    private static final Double DEFAULT_BEAN_RATE = 1D;
    private static final Double UPDATED_BEAN_RATE = 2D;

    private static final State DEFAULT_STATE = State.ON;
    private static final State UPDATED_STATE = State.OFF;

    @Autowired
    private ChickenInfoRepository chickenInfoRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restChickenInfoMockMvc;

    private ChickenInfo chickenInfo;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ChickenInfoResource chickenInfoResource = new ChickenInfoResource(chickenInfoRepository);
        this.restChickenInfoMockMvc = MockMvcBuilders.standaloneSetup(chickenInfoResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ChickenInfo createEntity(EntityManager em) {
        ChickenInfo chickenInfo = new ChickenInfo()
            .userName(DEFAULT_USER_NAME)
            .nickName(DEFAULT_NICK_NAME)
            .starId(DEFAULT_STAR_ID)
            .regDate(DEFAULT_REG_DATE)
            .cookie(DEFAULT_COOKIE)
            .timeRate(DEFAULT_TIME_RATE)
            .beanRate(DEFAULT_BEAN_RATE)
            .state(DEFAULT_STATE);
        return chickenInfo;
    }

    @Before
    public void initTest() {
        chickenInfo = createEntity(em);
    }

    @Test
    @Transactional
    public void createChickenInfo() throws Exception {
        int databaseSizeBeforeCreate = chickenInfoRepository.findAll().size();

        // Create the ChickenInfo
        restChickenInfoMockMvc.perform(post("/api/chicken-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(chickenInfo)))
            .andExpect(status().isCreated());

        // Validate the ChickenInfo in the database
        List<ChickenInfo> chickenInfoList = chickenInfoRepository.findAll();
        assertThat(chickenInfoList).hasSize(databaseSizeBeforeCreate + 1);
        ChickenInfo testChickenInfo = chickenInfoList.get(chickenInfoList.size() - 1);
        assertThat(testChickenInfo.getUserName()).isEqualTo(DEFAULT_USER_NAME);
        assertThat(testChickenInfo.getNickName()).isEqualTo(DEFAULT_NICK_NAME);
        assertThat(testChickenInfo.getStarId()).isEqualTo(DEFAULT_STAR_ID);
        assertThat(testChickenInfo.getRegDate()).isEqualTo(DEFAULT_REG_DATE);
        assertThat(testChickenInfo.getCookie()).isEqualTo(DEFAULT_COOKIE);
        assertThat(testChickenInfo.getTimeRate()).isEqualTo(DEFAULT_TIME_RATE);
        assertThat(testChickenInfo.getBeanRate()).isEqualTo(DEFAULT_BEAN_RATE);
        assertThat(testChickenInfo.getState()).isEqualTo(DEFAULT_STATE);
    }

    @Test
    @Transactional
    public void createChickenInfoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = chickenInfoRepository.findAll().size();

        // Create the ChickenInfo with an existing ID
        chickenInfo.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restChickenInfoMockMvc.perform(post("/api/chicken-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(chickenInfo)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<ChickenInfo> chickenInfoList = chickenInfoRepository.findAll();
        assertThat(chickenInfoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkUserNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = chickenInfoRepository.findAll().size();
        // set the field null
        chickenInfo.setUserName(null);

        // Create the ChickenInfo, which fails.

        restChickenInfoMockMvc.perform(post("/api/chicken-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(chickenInfo)))
            .andExpect(status().isBadRequest());

        List<ChickenInfo> chickenInfoList = chickenInfoRepository.findAll();
        assertThat(chickenInfoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNickNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = chickenInfoRepository.findAll().size();
        // set the field null
        chickenInfo.setNickName(null);

        // Create the ChickenInfo, which fails.

        restChickenInfoMockMvc.perform(post("/api/chicken-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(chickenInfo)))
            .andExpect(status().isBadRequest());

        List<ChickenInfo> chickenInfoList = chickenInfoRepository.findAll();
        assertThat(chickenInfoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStarIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = chickenInfoRepository.findAll().size();
        // set the field null
        chickenInfo.setStarId(null);

        // Create the ChickenInfo, which fails.

        restChickenInfoMockMvc.perform(post("/api/chicken-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(chickenInfo)))
            .andExpect(status().isBadRequest());

        List<ChickenInfo> chickenInfoList = chickenInfoRepository.findAll();
        assertThat(chickenInfoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkRegDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = chickenInfoRepository.findAll().size();
        // set the field null
        chickenInfo.setRegDate(null);

        // Create the ChickenInfo, which fails.

        restChickenInfoMockMvc.perform(post("/api/chicken-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(chickenInfo)))
            .andExpect(status().isBadRequest());

        List<ChickenInfo> chickenInfoList = chickenInfoRepository.findAll();
        assertThat(chickenInfoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCookieIsRequired() throws Exception {
        int databaseSizeBeforeTest = chickenInfoRepository.findAll().size();
        // set the field null
        chickenInfo.setCookie(null);

        // Create the ChickenInfo, which fails.

        restChickenInfoMockMvc.perform(post("/api/chicken-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(chickenInfo)))
            .andExpect(status().isBadRequest());

        List<ChickenInfo> chickenInfoList = chickenInfoRepository.findAll();
        assertThat(chickenInfoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTimeRateIsRequired() throws Exception {
        int databaseSizeBeforeTest = chickenInfoRepository.findAll().size();
        // set the field null
        chickenInfo.setTimeRate(null);

        // Create the ChickenInfo, which fails.

        restChickenInfoMockMvc.perform(post("/api/chicken-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(chickenInfo)))
            .andExpect(status().isBadRequest());

        List<ChickenInfo> chickenInfoList = chickenInfoRepository.findAll();
        assertThat(chickenInfoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkBeanRateIsRequired() throws Exception {
        int databaseSizeBeforeTest = chickenInfoRepository.findAll().size();
        // set the field null
        chickenInfo.setBeanRate(null);

        // Create the ChickenInfo, which fails.

        restChickenInfoMockMvc.perform(post("/api/chicken-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(chickenInfo)))
            .andExpect(status().isBadRequest());

        List<ChickenInfo> chickenInfoList = chickenInfoRepository.findAll();
        assertThat(chickenInfoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStateIsRequired() throws Exception {
        int databaseSizeBeforeTest = chickenInfoRepository.findAll().size();
        // set the field null
        chickenInfo.setState(null);

        // Create the ChickenInfo, which fails.

        restChickenInfoMockMvc.perform(post("/api/chicken-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(chickenInfo)))
            .andExpect(status().isBadRequest());

        List<ChickenInfo> chickenInfoList = chickenInfoRepository.findAll();
        assertThat(chickenInfoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllChickenInfos() throws Exception {
        // Initialize the database
        chickenInfoRepository.saveAndFlush(chickenInfo);

        // Get all the chickenInfoList
        restChickenInfoMockMvc.perform(get("/api/chicken-infos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(chickenInfo.getId().intValue())))
            .andExpect(jsonPath("$.[*].userName").value(hasItem(DEFAULT_USER_NAME.toString())))
            .andExpect(jsonPath("$.[*].nickName").value(hasItem(DEFAULT_NICK_NAME.toString())))
            .andExpect(jsonPath("$.[*].starId").value(hasItem(DEFAULT_STAR_ID)))
            .andExpect(jsonPath("$.[*].regDate").value(hasItem(DEFAULT_REG_DATE.toString())))
            .andExpect(jsonPath("$.[*].cookie").value(hasItem(DEFAULT_COOKIE.toString())))
            .andExpect(jsonPath("$.[*].timeRate").value(hasItem(DEFAULT_TIME_RATE.doubleValue())))
            .andExpect(jsonPath("$.[*].beanRate").value(hasItem(DEFAULT_BEAN_RATE.doubleValue())))
            .andExpect(jsonPath("$.[*].state").value(hasItem(DEFAULT_STATE.toString())));
    }

    @Test
    @Transactional
    public void getChickenInfo() throws Exception {
        // Initialize the database
        chickenInfoRepository.saveAndFlush(chickenInfo);

        // Get the chickenInfo
        restChickenInfoMockMvc.perform(get("/api/chicken-infos/{id}", chickenInfo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(chickenInfo.getId().intValue()))
            .andExpect(jsonPath("$.userName").value(DEFAULT_USER_NAME.toString()))
            .andExpect(jsonPath("$.nickName").value(DEFAULT_NICK_NAME.toString()))
            .andExpect(jsonPath("$.starId").value(DEFAULT_STAR_ID))
            .andExpect(jsonPath("$.regDate").value(DEFAULT_REG_DATE.toString()))
            .andExpect(jsonPath("$.cookie").value(DEFAULT_COOKIE.toString()))
            .andExpect(jsonPath("$.timeRate").value(DEFAULT_TIME_RATE.doubleValue()))
            .andExpect(jsonPath("$.beanRate").value(DEFAULT_BEAN_RATE.doubleValue()))
            .andExpect(jsonPath("$.state").value(DEFAULT_STATE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingChickenInfo() throws Exception {
        // Get the chickenInfo
        restChickenInfoMockMvc.perform(get("/api/chicken-infos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateChickenInfo() throws Exception {
        // Initialize the database
        chickenInfoRepository.saveAndFlush(chickenInfo);
        int databaseSizeBeforeUpdate = chickenInfoRepository.findAll().size();

        // Update the chickenInfo
        ChickenInfo updatedChickenInfo = chickenInfoRepository.findOne(chickenInfo.getId());
        updatedChickenInfo
            .userName(UPDATED_USER_NAME)
            .nickName(UPDATED_NICK_NAME)
            .starId(UPDATED_STAR_ID)
            .regDate(UPDATED_REG_DATE)
            .cookie(UPDATED_COOKIE)
            .timeRate(UPDATED_TIME_RATE)
            .beanRate(UPDATED_BEAN_RATE)
            .state(UPDATED_STATE);

        restChickenInfoMockMvc.perform(put("/api/chicken-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedChickenInfo)))
            .andExpect(status().isOk());

        // Validate the ChickenInfo in the database
        List<ChickenInfo> chickenInfoList = chickenInfoRepository.findAll();
        assertThat(chickenInfoList).hasSize(databaseSizeBeforeUpdate);
        ChickenInfo testChickenInfo = chickenInfoList.get(chickenInfoList.size() - 1);
        assertThat(testChickenInfo.getUserName()).isEqualTo(UPDATED_USER_NAME);
        assertThat(testChickenInfo.getNickName()).isEqualTo(UPDATED_NICK_NAME);
        assertThat(testChickenInfo.getStarId()).isEqualTo(UPDATED_STAR_ID);
        assertThat(testChickenInfo.getRegDate()).isEqualTo(UPDATED_REG_DATE);
        assertThat(testChickenInfo.getCookie()).isEqualTo(UPDATED_COOKIE);
        assertThat(testChickenInfo.getTimeRate()).isEqualTo(UPDATED_TIME_RATE);
        assertThat(testChickenInfo.getBeanRate()).isEqualTo(UPDATED_BEAN_RATE);
        assertThat(testChickenInfo.getState()).isEqualTo(UPDATED_STATE);
    }

    @Test
    @Transactional
    public void updateNonExistingChickenInfo() throws Exception {
        int databaseSizeBeforeUpdate = chickenInfoRepository.findAll().size();

        // Create the ChickenInfo

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restChickenInfoMockMvc.perform(put("/api/chicken-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(chickenInfo)))
            .andExpect(status().isCreated());

        // Validate the ChickenInfo in the database
        List<ChickenInfo> chickenInfoList = chickenInfoRepository.findAll();
        assertThat(chickenInfoList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteChickenInfo() throws Exception {
        // Initialize the database
        chickenInfoRepository.saveAndFlush(chickenInfo);
        int databaseSizeBeforeDelete = chickenInfoRepository.findAll().size();

        // Get the chickenInfo
        restChickenInfoMockMvc.perform(delete("/api/chicken-infos/{id}", chickenInfo.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ChickenInfo> chickenInfoList = chickenInfoRepository.findAll();
        assertThat(chickenInfoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ChickenInfo.class);
        ChickenInfo chickenInfo1 = new ChickenInfo();
        chickenInfo1.setId(1L);
        ChickenInfo chickenInfo2 = new ChickenInfo();
        chickenInfo2.setId(chickenInfo1.getId());
        assertThat(chickenInfo1).isEqualTo(chickenInfo2);
        chickenInfo2.setId(2L);
        assertThat(chickenInfo1).isNotEqualTo(chickenInfo2);
        chickenInfo1.setId(null);
        assertThat(chickenInfo1).isNotEqualTo(chickenInfo2);
    }
}
