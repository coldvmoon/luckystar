package com.luckystar.web.web.rest;

import com.luckystar.web.LuckystarApp;

import com.luckystar.web.domain.WorkInfo;
import com.luckystar.web.repository.WorkInfoRepository;
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
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import java.time.ZoneId;
import java.util.List;

import static com.luckystar.web.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the WorkInfoResource REST controller.
 *
 * @see WorkInfoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = LuckystarApp.class)
public class WorkInfoResourceIntTest {

    private static final Long DEFAULT_STAR_ID = 1L;
    private static final Long UPDATED_STAR_ID = 2L;

    private static final Integer DEFAULT_STAR_LEVEL = 1;
    private static final Integer UPDATED_STAR_LEVEL = 2;

    private static final String DEFAULT_STAR_NAME = "AAAAAAAAAA";
    private static final String UPDATED_STAR_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_RICH_LEVEL = 1;
    private static final Integer UPDATED_RICH_LEVEL = 2;

    private static final String DEFAULT_RICH_NAME = "AAAAAAAAAA";
    private static final String UPDATED_RICH_NAME = "BBBBBBBBBB";

    private static final Float DEFAULT_FISRT_BEAN = 1F;
    private static final Float UPDATED_FISRT_BEAN = 2F;

    private static final Float DEFAULT_BEAN_TOTAL = 1F;
    private static final Float UPDATED_BEAN_TOTAL = 2F;

    private static final Float DEFAULT_COIN = 1F;
    private static final Float UPDATED_COIN = 2F;

    private static final Float DEFAULT_COIN_TOTAL = 1F;
    private static final Float UPDATED_COIN_TOTAL = 2F;

    private static final Integer DEFAULT_FANS_COUNT = 1;
    private static final Integer UPDATED_FANS_COUNT = 2;

    private static final Integer DEFAULT_FOLLOW_COUNT = 1;
    private static final Integer UPDATED_FOLLOW_COUNT = 2;

    private static final Float DEFAULT_EXPERIENCE = 1F;
    private static final Float UPDATED_EXPERIENCE = 2F;

    private static final Integer DEFAULT_WORK_TIME = 1;
    private static final Integer UPDATED_WORK_TIME = 2;

    private static final Integer DEFAULT_CUR_MONTH = 1;
    private static final Integer UPDATED_CUR_MONTH = 2;

    private static final LocalDate DEFAULT_CUR_DAY = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CUR_DAY = LocalDate.now(ZoneId.systemDefault());

    private static final ZonedDateTime DEFAULT_LAST_TIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_LAST_TIME = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    @Autowired
    private WorkInfoRepository workInfoRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restWorkInfoMockMvc;

    private WorkInfo workInfo;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        WorkInfoResource workInfoResource = new WorkInfoResource(workInfoRepository);
        this.restWorkInfoMockMvc = MockMvcBuilders.standaloneSetup(workInfoResource)
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
    public static WorkInfo createEntity(EntityManager em) {
        WorkInfo workInfo = new WorkInfo()
            .starId(DEFAULT_STAR_ID)
            .starLevel(DEFAULT_STAR_LEVEL)
            .starName(DEFAULT_STAR_NAME)
            .richLevel(DEFAULT_RICH_LEVEL)
            .richName(DEFAULT_RICH_NAME)
            .fisrtBean(DEFAULT_FISRT_BEAN)
            .beanTotal(DEFAULT_BEAN_TOTAL)
            .coin(DEFAULT_COIN)
            .coinTotal(DEFAULT_COIN_TOTAL)
            .fansCount(DEFAULT_FANS_COUNT)
            .followCount(DEFAULT_FOLLOW_COUNT)
            .experience(DEFAULT_EXPERIENCE)
            .workTime(DEFAULT_WORK_TIME)
            .curMonth(DEFAULT_CUR_MONTH)
            .curDay(DEFAULT_CUR_DAY)
            .lastTime(DEFAULT_LAST_TIME);
        return workInfo;
    }

    @Before
    public void initTest() {
        workInfo = createEntity(em);
    }

    @Test
    @Transactional
    public void createWorkInfo() throws Exception {
        int databaseSizeBeforeCreate = workInfoRepository.findAll().size();

        // Create the WorkInfo
        restWorkInfoMockMvc.perform(post("/api/work-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(workInfo)))
            .andExpect(status().isCreated());

        // Validate the WorkInfo in the database
        List<WorkInfo> workInfoList = workInfoRepository.findAll();
        assertThat(workInfoList).hasSize(databaseSizeBeforeCreate + 1);
        WorkInfo testWorkInfo = workInfoList.get(workInfoList.size() - 1);
        assertThat(testWorkInfo.getStarId()).isEqualTo(DEFAULT_STAR_ID);
        assertThat(testWorkInfo.getStarLevel()).isEqualTo(DEFAULT_STAR_LEVEL);
        assertThat(testWorkInfo.getStarName()).isEqualTo(DEFAULT_STAR_NAME);
        assertThat(testWorkInfo.getRichLevel()).isEqualTo(DEFAULT_RICH_LEVEL);
        assertThat(testWorkInfo.getRichName()).isEqualTo(DEFAULT_RICH_NAME);
        assertThat(testWorkInfo.getFisrtBean()).isEqualTo(DEFAULT_FISRT_BEAN);
        assertThat(testWorkInfo.getBeanTotal()).isEqualTo(DEFAULT_BEAN_TOTAL);
        assertThat(testWorkInfo.getCoin()).isEqualTo(DEFAULT_COIN);
        assertThat(testWorkInfo.getCoinTotal()).isEqualTo(DEFAULT_COIN_TOTAL);
        assertThat(testWorkInfo.getFansCount()).isEqualTo(DEFAULT_FANS_COUNT);
        assertThat(testWorkInfo.getFollowCount()).isEqualTo(DEFAULT_FOLLOW_COUNT);
        assertThat(testWorkInfo.getExperience()).isEqualTo(DEFAULT_EXPERIENCE);
        assertThat(testWorkInfo.getWorkTime()).isEqualTo(DEFAULT_WORK_TIME);
        assertThat(testWorkInfo.getCurMonth()).isEqualTo(DEFAULT_CUR_MONTH);
        assertThat(testWorkInfo.getCurDay()).isEqualTo(DEFAULT_CUR_DAY);
        assertThat(testWorkInfo.getLastTime()).isEqualTo(DEFAULT_LAST_TIME);
    }

    @Test
    @Transactional
    public void createWorkInfoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = workInfoRepository.findAll().size();

        // Create the WorkInfo with an existing ID
        workInfo.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restWorkInfoMockMvc.perform(post("/api/work-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(workInfo)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<WorkInfo> workInfoList = workInfoRepository.findAll();
        assertThat(workInfoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkStarIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = workInfoRepository.findAll().size();
        // set the field null
        workInfo.setStarId(null);

        // Create the WorkInfo, which fails.

        restWorkInfoMockMvc.perform(post("/api/work-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(workInfo)))
            .andExpect(status().isBadRequest());

        List<WorkInfo> workInfoList = workInfoRepository.findAll();
        assertThat(workInfoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCurMonthIsRequired() throws Exception {
        int databaseSizeBeforeTest = workInfoRepository.findAll().size();
        // set the field null
        workInfo.setCurMonth(null);

        // Create the WorkInfo, which fails.

        restWorkInfoMockMvc.perform(post("/api/work-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(workInfo)))
            .andExpect(status().isBadRequest());

        List<WorkInfo> workInfoList = workInfoRepository.findAll();
        assertThat(workInfoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCurDayIsRequired() throws Exception {
        int databaseSizeBeforeTest = workInfoRepository.findAll().size();
        // set the field null
        workInfo.setCurDay(null);

        // Create the WorkInfo, which fails.

        restWorkInfoMockMvc.perform(post("/api/work-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(workInfo)))
            .andExpect(status().isBadRequest());

        List<WorkInfo> workInfoList = workInfoRepository.findAll();
        assertThat(workInfoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLastTimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = workInfoRepository.findAll().size();
        // set the field null
        workInfo.setLastTime(null);

        // Create the WorkInfo, which fails.

        restWorkInfoMockMvc.perform(post("/api/work-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(workInfo)))
            .andExpect(status().isBadRequest());

        List<WorkInfo> workInfoList = workInfoRepository.findAll();
        assertThat(workInfoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllWorkInfos() throws Exception {
        // Initialize the database
        workInfoRepository.saveAndFlush(workInfo);

        // Get all the workInfoList
        restWorkInfoMockMvc.perform(get("/api/work-infos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(workInfo.getId().intValue())))
            .andExpect(jsonPath("$.[*].starId").value(hasItem(DEFAULT_STAR_ID.intValue())))
            .andExpect(jsonPath("$.[*].starLevel").value(hasItem(DEFAULT_STAR_LEVEL)))
            .andExpect(jsonPath("$.[*].starName").value(hasItem(DEFAULT_STAR_NAME.toString())))
            .andExpect(jsonPath("$.[*].richLevel").value(hasItem(DEFAULT_RICH_LEVEL)))
            .andExpect(jsonPath("$.[*].richName").value(hasItem(DEFAULT_RICH_NAME.toString())))
            .andExpect(jsonPath("$.[*].fisrtBean").value(hasItem(DEFAULT_FISRT_BEAN.doubleValue())))
            .andExpect(jsonPath("$.[*].beanTotal").value(hasItem(DEFAULT_BEAN_TOTAL.doubleValue())))
            .andExpect(jsonPath("$.[*].coin").value(hasItem(DEFAULT_COIN.doubleValue())))
            .andExpect(jsonPath("$.[*].coinTotal").value(hasItem(DEFAULT_COIN_TOTAL.doubleValue())))
            .andExpect(jsonPath("$.[*].fansCount").value(hasItem(DEFAULT_FANS_COUNT)))
            .andExpect(jsonPath("$.[*].followCount").value(hasItem(DEFAULT_FOLLOW_COUNT)))
            .andExpect(jsonPath("$.[*].experience").value(hasItem(DEFAULT_EXPERIENCE.doubleValue())))
            .andExpect(jsonPath("$.[*].workTime").value(hasItem(DEFAULT_WORK_TIME)))
            .andExpect(jsonPath("$.[*].curMonth").value(hasItem(DEFAULT_CUR_MONTH)))
            .andExpect(jsonPath("$.[*].curDay").value(hasItem(DEFAULT_CUR_DAY.toString())))
            .andExpect(jsonPath("$.[*].lastTime").value(hasItem(sameInstant(DEFAULT_LAST_TIME))));
    }

    @Test
    @Transactional
    public void getWorkInfo() throws Exception {
        // Initialize the database
        workInfoRepository.saveAndFlush(workInfo);

        // Get the workInfo
        restWorkInfoMockMvc.perform(get("/api/work-infos/{id}", workInfo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(workInfo.getId().intValue()))
            .andExpect(jsonPath("$.starId").value(DEFAULT_STAR_ID.intValue()))
            .andExpect(jsonPath("$.starLevel").value(DEFAULT_STAR_LEVEL))
            .andExpect(jsonPath("$.starName").value(DEFAULT_STAR_NAME.toString()))
            .andExpect(jsonPath("$.richLevel").value(DEFAULT_RICH_LEVEL))
            .andExpect(jsonPath("$.richName").value(DEFAULT_RICH_NAME.toString()))
            .andExpect(jsonPath("$.fisrtBean").value(DEFAULT_FISRT_BEAN.doubleValue()))
            .andExpect(jsonPath("$.beanTotal").value(DEFAULT_BEAN_TOTAL.doubleValue()))
            .andExpect(jsonPath("$.coin").value(DEFAULT_COIN.doubleValue()))
            .andExpect(jsonPath("$.coinTotal").value(DEFAULT_COIN_TOTAL.doubleValue()))
            .andExpect(jsonPath("$.fansCount").value(DEFAULT_FANS_COUNT))
            .andExpect(jsonPath("$.followCount").value(DEFAULT_FOLLOW_COUNT))
            .andExpect(jsonPath("$.experience").value(DEFAULT_EXPERIENCE.doubleValue()))
            .andExpect(jsonPath("$.workTime").value(DEFAULT_WORK_TIME))
            .andExpect(jsonPath("$.curMonth").value(DEFAULT_CUR_MONTH))
            .andExpect(jsonPath("$.curDay").value(DEFAULT_CUR_DAY.toString()))
            .andExpect(jsonPath("$.lastTime").value(sameInstant(DEFAULT_LAST_TIME)));
    }

    @Test
    @Transactional
    public void getNonExistingWorkInfo() throws Exception {
        // Get the workInfo
        restWorkInfoMockMvc.perform(get("/api/work-infos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateWorkInfo() throws Exception {
        // Initialize the database
        workInfoRepository.saveAndFlush(workInfo);
        int databaseSizeBeforeUpdate = workInfoRepository.findAll().size();

        // Update the workInfo
        WorkInfo updatedWorkInfo = workInfoRepository.findOne(workInfo.getId());
        updatedWorkInfo
            .starId(UPDATED_STAR_ID)
            .starLevel(UPDATED_STAR_LEVEL)
            .starName(UPDATED_STAR_NAME)
            .richLevel(UPDATED_RICH_LEVEL)
            .richName(UPDATED_RICH_NAME)
            .fisrtBean(UPDATED_FISRT_BEAN)
            .beanTotal(UPDATED_BEAN_TOTAL)
            .coin(UPDATED_COIN)
            .coinTotal(UPDATED_COIN_TOTAL)
            .fansCount(UPDATED_FANS_COUNT)
            .followCount(UPDATED_FOLLOW_COUNT)
            .experience(UPDATED_EXPERIENCE)
            .workTime(UPDATED_WORK_TIME)
            .curMonth(UPDATED_CUR_MONTH)
            .curDay(UPDATED_CUR_DAY)
            .lastTime(UPDATED_LAST_TIME);

        restWorkInfoMockMvc.perform(put("/api/work-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedWorkInfo)))
            .andExpect(status().isOk());

        // Validate the WorkInfo in the database
        List<WorkInfo> workInfoList = workInfoRepository.findAll();
        assertThat(workInfoList).hasSize(databaseSizeBeforeUpdate);
        WorkInfo testWorkInfo = workInfoList.get(workInfoList.size() - 1);
        assertThat(testWorkInfo.getStarId()).isEqualTo(UPDATED_STAR_ID);
        assertThat(testWorkInfo.getStarLevel()).isEqualTo(UPDATED_STAR_LEVEL);
        assertThat(testWorkInfo.getStarName()).isEqualTo(UPDATED_STAR_NAME);
        assertThat(testWorkInfo.getRichLevel()).isEqualTo(UPDATED_RICH_LEVEL);
        assertThat(testWorkInfo.getRichName()).isEqualTo(UPDATED_RICH_NAME);
        assertThat(testWorkInfo.getFisrtBean()).isEqualTo(UPDATED_FISRT_BEAN);
        assertThat(testWorkInfo.getBeanTotal()).isEqualTo(UPDATED_BEAN_TOTAL);
        assertThat(testWorkInfo.getCoin()).isEqualTo(UPDATED_COIN);
        assertThat(testWorkInfo.getCoinTotal()).isEqualTo(UPDATED_COIN_TOTAL);
        assertThat(testWorkInfo.getFansCount()).isEqualTo(UPDATED_FANS_COUNT);
        assertThat(testWorkInfo.getFollowCount()).isEqualTo(UPDATED_FOLLOW_COUNT);
        assertThat(testWorkInfo.getExperience()).isEqualTo(UPDATED_EXPERIENCE);
        assertThat(testWorkInfo.getWorkTime()).isEqualTo(UPDATED_WORK_TIME);
        assertThat(testWorkInfo.getCurMonth()).isEqualTo(UPDATED_CUR_MONTH);
        assertThat(testWorkInfo.getCurDay()).isEqualTo(UPDATED_CUR_DAY);
        assertThat(testWorkInfo.getLastTime()).isEqualTo(UPDATED_LAST_TIME);
    }

    @Test
    @Transactional
    public void updateNonExistingWorkInfo() throws Exception {
        int databaseSizeBeforeUpdate = workInfoRepository.findAll().size();

        // Create the WorkInfo

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restWorkInfoMockMvc.perform(put("/api/work-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(workInfo)))
            .andExpect(status().isCreated());

        // Validate the WorkInfo in the database
        List<WorkInfo> workInfoList = workInfoRepository.findAll();
        assertThat(workInfoList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteWorkInfo() throws Exception {
        // Initialize the database
        workInfoRepository.saveAndFlush(workInfo);
        int databaseSizeBeforeDelete = workInfoRepository.findAll().size();

        // Get the workInfo
        restWorkInfoMockMvc.perform(delete("/api/work-infos/{id}", workInfo.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<WorkInfo> workInfoList = workInfoRepository.findAll();
        assertThat(workInfoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(WorkInfo.class);
        WorkInfo workInfo1 = new WorkInfo();
        workInfo1.setId(1L);
        WorkInfo workInfo2 = new WorkInfo();
        workInfo2.setId(workInfo1.getId());
        assertThat(workInfo1).isEqualTo(workInfo2);
        workInfo2.setId(2L);
        assertThat(workInfo1).isNotEqualTo(workInfo2);
        workInfo1.setId(null);
        assertThat(workInfo1).isNotEqualTo(workInfo2);
    }
}
