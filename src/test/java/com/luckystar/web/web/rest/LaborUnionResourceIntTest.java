package com.luckystar.web.web.rest;

import com.luckystar.web.LuckystarApp;

import com.luckystar.web.domain.LaborUnion;
import com.luckystar.web.repository.LaborUnionRepository;
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
import com.luckystar.web.domain.enumeration.Source;
/**
 * Test class for the LaborUnionResource REST controller.
 *
 * @see LaborUnionResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = LuckystarApp.class)
public class LaborUnionResourceIntTest {

    private static final Integer DEFAULT_L_ID = 1;
    private static final Integer UPDATED_L_ID = 2;

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_REG_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_REG_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final State DEFAULT_STATE = State.OFF;
    private static final State UPDATED_STATE = State.ON;

    private static final Source DEFAULT_TYPE = Source.FANXIN;
    private static final Source UPDATED_TYPE = Source.FANXIN;

    @Autowired
    private LaborUnionRepository laborUnionRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restLaborUnionMockMvc;

    private LaborUnion laborUnion;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        LaborUnionResource laborUnionResource = new LaborUnionResource(laborUnionRepository);
        this.restLaborUnionMockMvc = MockMvcBuilders.standaloneSetup(laborUnionResource)
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
    public static LaborUnion createEntity(EntityManager em) {
        LaborUnion laborUnion = new LaborUnion()
            .lId(DEFAULT_L_ID)
            .name(DEFAULT_NAME)
            .regDate(DEFAULT_REG_DATE)
            .state(DEFAULT_STATE)
            .type(DEFAULT_TYPE);
        return laborUnion;
    }

    @Before
    public void initTest() {
        laborUnion = createEntity(em);
    }

    @Test
    @Transactional
    public void createLaborUnion() throws Exception {
        int databaseSizeBeforeCreate = laborUnionRepository.findAll().size();

        // Create the LaborUnion
        restLaborUnionMockMvc.perform(post("/api/labor-unions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(laborUnion)))
            .andExpect(status().isCreated());

        // Validate the LaborUnion in the database
        List<LaborUnion> laborUnionList = laborUnionRepository.findAll();
        assertThat(laborUnionList).hasSize(databaseSizeBeforeCreate + 1);
        LaborUnion testLaborUnion = laborUnionList.get(laborUnionList.size() - 1);
        assertThat(testLaborUnion.getlId()).isEqualTo(DEFAULT_L_ID);
        assertThat(testLaborUnion.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testLaborUnion.getRegDate()).isEqualTo(DEFAULT_REG_DATE);
        assertThat(testLaborUnion.getState()).isEqualTo(DEFAULT_STATE);
        assertThat(testLaborUnion.getType()).isEqualTo(DEFAULT_TYPE);
    }

    @Test
    @Transactional
    public void createLaborUnionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = laborUnionRepository.findAll().size();

        // Create the LaborUnion with an existing ID
        laborUnion.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLaborUnionMockMvc.perform(post("/api/labor-unions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(laborUnion)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<LaborUnion> laborUnionList = laborUnionRepository.findAll();
        assertThat(laborUnionList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checklIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = laborUnionRepository.findAll().size();
        // set the field null
        laborUnion.setlId(null);

        // Create the LaborUnion, which fails.

        restLaborUnionMockMvc.perform(post("/api/labor-unions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(laborUnion)))
            .andExpect(status().isBadRequest());

        List<LaborUnion> laborUnionList = laborUnionRepository.findAll();
        assertThat(laborUnionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = laborUnionRepository.findAll().size();
        // set the field null
        laborUnion.setName(null);

        // Create the LaborUnion, which fails.

        restLaborUnionMockMvc.perform(post("/api/labor-unions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(laborUnion)))
            .andExpect(status().isBadRequest());

        List<LaborUnion> laborUnionList = laborUnionRepository.findAll();
        assertThat(laborUnionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkRegDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = laborUnionRepository.findAll().size();
        // set the field null
        laborUnion.setRegDate(null);

        // Create the LaborUnion, which fails.

        restLaborUnionMockMvc.perform(post("/api/labor-unions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(laborUnion)))
            .andExpect(status().isBadRequest());

        List<LaborUnion> laborUnionList = laborUnionRepository.findAll();
        assertThat(laborUnionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStateIsRequired() throws Exception {
        int databaseSizeBeforeTest = laborUnionRepository.findAll().size();
        // set the field null
        laborUnion.setState(null);

        // Create the LaborUnion, which fails.

        restLaborUnionMockMvc.perform(post("/api/labor-unions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(laborUnion)))
            .andExpect(status().isBadRequest());

        List<LaborUnion> laborUnionList = laborUnionRepository.findAll();
        assertThat(laborUnionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = laborUnionRepository.findAll().size();
        // set the field null
        laborUnion.setType(null);

        // Create the LaborUnion, which fails.

        restLaborUnionMockMvc.perform(post("/api/labor-unions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(laborUnion)))
            .andExpect(status().isBadRequest());

        List<LaborUnion> laborUnionList = laborUnionRepository.findAll();
        assertThat(laborUnionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllLaborUnions() throws Exception {
        // Initialize the database
        laborUnionRepository.saveAndFlush(laborUnion);

        // Get all the laborUnionList
        restLaborUnionMockMvc.perform(get("/api/labor-unions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(laborUnion.getId().intValue())))
            .andExpect(jsonPath("$.[*].lId").value(hasItem(DEFAULT_L_ID)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].regDate").value(hasItem(DEFAULT_REG_DATE.toString())))
            .andExpect(jsonPath("$.[*].state").value(hasItem(DEFAULT_STATE.toString())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())));
    }

    @Test
    @Transactional
    public void getLaborUnion() throws Exception {
        // Initialize the database
        laborUnionRepository.saveAndFlush(laborUnion);

        // Get the laborUnion
        restLaborUnionMockMvc.perform(get("/api/labor-unions/{id}", laborUnion.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(laborUnion.getId().intValue()))
            .andExpect(jsonPath("$.lId").value(DEFAULT_L_ID))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.regDate").value(DEFAULT_REG_DATE.toString()))
            .andExpect(jsonPath("$.state").value(DEFAULT_STATE.toString()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingLaborUnion() throws Exception {
        // Get the laborUnion
        restLaborUnionMockMvc.perform(get("/api/labor-unions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLaborUnion() throws Exception {
        // Initialize the database
        laborUnionRepository.saveAndFlush(laborUnion);
        int databaseSizeBeforeUpdate = laborUnionRepository.findAll().size();

        // Update the laborUnion
        LaborUnion updatedLaborUnion = laborUnionRepository.findOne(laborUnion.getId());
        updatedLaborUnion
            .lId(UPDATED_L_ID)
            .name(UPDATED_NAME)
            .regDate(UPDATED_REG_DATE)
            .state(UPDATED_STATE)
            .type(UPDATED_TYPE);

        restLaborUnionMockMvc.perform(put("/api/labor-unions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedLaborUnion)))
            .andExpect(status().isOk());

        // Validate the LaborUnion in the database
        List<LaborUnion> laborUnionList = laborUnionRepository.findAll();
        assertThat(laborUnionList).hasSize(databaseSizeBeforeUpdate);
        LaborUnion testLaborUnion = laborUnionList.get(laborUnionList.size() - 1);
        assertThat(testLaborUnion.getlId()).isEqualTo(UPDATED_L_ID);
        assertThat(testLaborUnion.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testLaborUnion.getRegDate()).isEqualTo(UPDATED_REG_DATE);
        assertThat(testLaborUnion.getState()).isEqualTo(UPDATED_STATE);
        assertThat(testLaborUnion.getType()).isEqualTo(UPDATED_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingLaborUnion() throws Exception {
        int databaseSizeBeforeUpdate = laborUnionRepository.findAll().size();

        // Create the LaborUnion

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restLaborUnionMockMvc.perform(put("/api/labor-unions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(laborUnion)))
            .andExpect(status().isCreated());

        // Validate the LaborUnion in the database
        List<LaborUnion> laborUnionList = laborUnionRepository.findAll();
        assertThat(laborUnionList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteLaborUnion() throws Exception {
        // Initialize the database
        laborUnionRepository.saveAndFlush(laborUnion);
        int databaseSizeBeforeDelete = laborUnionRepository.findAll().size();

        // Get the laborUnion
        restLaborUnionMockMvc.perform(delete("/api/labor-unions/{id}", laborUnion.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<LaborUnion> laborUnionList = laborUnionRepository.findAll();
        assertThat(laborUnionList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(LaborUnion.class);
        LaborUnion laborUnion1 = new LaborUnion();
        laborUnion1.setId(1L);
        LaborUnion laborUnion2 = new LaborUnion();
        laborUnion2.setId(laborUnion1.getId());
        assertThat(laborUnion1).isEqualTo(laborUnion2);
        laborUnion2.setId(2L);
        assertThat(laborUnion1).isNotEqualTo(laborUnion2);
        laborUnion1.setId(null);
        assertThat(laborUnion1).isNotEqualTo(laborUnion2);
    }
}
