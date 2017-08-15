package com.luckystar.web.web.rest;

import com.luckystar.web.LuckystarApp;

import com.luckystar.web.domain.UserInfo;
import com.luckystar.web.repository.UserInfoRepository;
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
 * Test class for the UserInfoResource REST controller.
 *
 * @see UserInfoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = LuckystarApp.class)
public class UserInfoResourceIntTest {

    private static final String DEFAULT_USER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_USER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_NICK_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NICK_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PHONE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_PHONE_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_QQ = "AAAAAAAAAA";
    private static final String UPDATED_QQ = "BBBBBBBBBB";

    private static final String DEFAULT_WEI_CHAT = "AAAAAAAAAA";
    private static final String UPDATED_WEI_CHAT = "BBBBBBBBBB";

    private static final String DEFAULT_STAR_ID = "AAAAAAAAAA";
    private static final String UPDATED_STAR_ID = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_REG_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_REG_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_LOGIN_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LOGIN_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PASSWORD = "AAAAAAAAAA";
    private static final String UPDATED_PASSWORD = "BBBBBBBBBB";

    private static final String DEFAULT_COOKIE = "AAAAAAAAAA";
    private static final String UPDATED_COOKIE = "BBBBBBBBBB";

    private static final Float DEFAULT_TIME_RATE = 1F;
    private static final Float UPDATED_TIME_RATE = 2F;

    private static final Float DEFAULT_BEAN_RATE = 1F;
    private static final Float UPDATED_BEAN_RATE = 2F;

    private static final LocalDate DEFAULT_LAST_MAINTAIN = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_LAST_MAINTAIN = LocalDate.now(ZoneId.systemDefault());

    private static final State DEFAULT_STATE = State.OFF;
    private static final State UPDATED_STATE = State.ON;

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restUserInfoMockMvc;

    private UserInfo userInfo;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        UserInfoResource userInfoResource = new UserInfoResource(userInfoRepository);
        this.restUserInfoMockMvc = MockMvcBuilders.standaloneSetup(userInfoResource)
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
    public static UserInfo createEntity(EntityManager em) {
        UserInfo userInfo = new UserInfo()
            .userName(DEFAULT_USER_NAME)
            .nickName(DEFAULT_NICK_NAME)
            .phoneNumber(DEFAULT_PHONE_NUMBER)
            .qq(DEFAULT_QQ)
            .weiChat(DEFAULT_WEI_CHAT)
            .starId(DEFAULT_STAR_ID)
            .regDate(DEFAULT_REG_DATE)
            .loginName(DEFAULT_LOGIN_NAME)
            .password(DEFAULT_PASSWORD)
            .cookie(DEFAULT_COOKIE)
            .timeRate(DEFAULT_TIME_RATE)
            .beanRate(DEFAULT_BEAN_RATE)
            .lastMaintain(DEFAULT_LAST_MAINTAIN)
            .state(DEFAULT_STATE);
        return userInfo;
    }

    @Before
    public void initTest() {
        userInfo = createEntity(em);
    }

    @Test
    @Transactional
    public void createUserInfo() throws Exception {
        int databaseSizeBeforeCreate = userInfoRepository.findAll().size();

        // Create the UserInfo
        restUserInfoMockMvc.perform(post("/api/user-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userInfo)))
            .andExpect(status().isCreated());

        // Validate the UserInfo in the database
        List<UserInfo> userInfoList = userInfoRepository.findAll();
        assertThat(userInfoList).hasSize(databaseSizeBeforeCreate + 1);
        UserInfo testUserInfo = userInfoList.get(userInfoList.size() - 1);
        assertThat(testUserInfo.getUserName()).isEqualTo(DEFAULT_USER_NAME);
        assertThat(testUserInfo.getNickName()).isEqualTo(DEFAULT_NICK_NAME);
        assertThat(testUserInfo.getPhoneNumber()).isEqualTo(DEFAULT_PHONE_NUMBER);
        assertThat(testUserInfo.getQq()).isEqualTo(DEFAULT_QQ);
        assertThat(testUserInfo.getWeiChat()).isEqualTo(DEFAULT_WEI_CHAT);
        assertThat(testUserInfo.getStarId()).isEqualTo(DEFAULT_STAR_ID);
        assertThat(testUserInfo.getRegDate()).isEqualTo(DEFAULT_REG_DATE);
        assertThat(testUserInfo.getLoginName()).isEqualTo(DEFAULT_LOGIN_NAME);
        assertThat(testUserInfo.getPassword()).isEqualTo(DEFAULT_PASSWORD);
        assertThat(testUserInfo.getCookie()).isEqualTo(DEFAULT_COOKIE);
        assertThat(testUserInfo.getTimeRate()).isEqualTo(DEFAULT_TIME_RATE);
        assertThat(testUserInfo.getBeanRate()).isEqualTo(DEFAULT_BEAN_RATE);
        assertThat(testUserInfo.getLastMaintain()).isEqualTo(DEFAULT_LAST_MAINTAIN);
        assertThat(testUserInfo.getState()).isEqualTo(DEFAULT_STATE);
    }

    @Test
    @Transactional
    public void createUserInfoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = userInfoRepository.findAll().size();

        // Create the UserInfo with an existing ID
        userInfo.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUserInfoMockMvc.perform(post("/api/user-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userInfo)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<UserInfo> userInfoList = userInfoRepository.findAll();
        assertThat(userInfoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkUserNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = userInfoRepository.findAll().size();
        // set the field null
        userInfo.setUserName(null);

        // Create the UserInfo, which fails.

        restUserInfoMockMvc.perform(post("/api/user-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userInfo)))
            .andExpect(status().isBadRequest());

        List<UserInfo> userInfoList = userInfoRepository.findAll();
        assertThat(userInfoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStarIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = userInfoRepository.findAll().size();
        // set the field null
        userInfo.setStarId(null);

        // Create the UserInfo, which fails.

        restUserInfoMockMvc.perform(post("/api/user-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userInfo)))
            .andExpect(status().isBadRequest());

        List<UserInfo> userInfoList = userInfoRepository.findAll();
        assertThat(userInfoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkRegDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = userInfoRepository.findAll().size();
        // set the field null
        userInfo.setRegDate(null);

        // Create the UserInfo, which fails.

        restUserInfoMockMvc.perform(post("/api/user-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userInfo)))
            .andExpect(status().isBadRequest());

        List<UserInfo> userInfoList = userInfoRepository.findAll();
        assertThat(userInfoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLoginNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = userInfoRepository.findAll().size();
        // set the field null
        userInfo.setLoginName(null);

        // Create the UserInfo, which fails.

        restUserInfoMockMvc.perform(post("/api/user-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userInfo)))
            .andExpect(status().isBadRequest());

        List<UserInfo> userInfoList = userInfoRepository.findAll();
        assertThat(userInfoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPasswordIsRequired() throws Exception {
        int databaseSizeBeforeTest = userInfoRepository.findAll().size();
        // set the field null
        userInfo.setPassword(null);

        // Create the UserInfo, which fails.

        restUserInfoMockMvc.perform(post("/api/user-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userInfo)))
            .andExpect(status().isBadRequest());

        List<UserInfo> userInfoList = userInfoRepository.findAll();
        assertThat(userInfoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTimeRateIsRequired() throws Exception {
        int databaseSizeBeforeTest = userInfoRepository.findAll().size();
        // set the field null
        userInfo.setTimeRate(null);

        // Create the UserInfo, which fails.

        restUserInfoMockMvc.perform(post("/api/user-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userInfo)))
            .andExpect(status().isBadRequest());

        List<UserInfo> userInfoList = userInfoRepository.findAll();
        assertThat(userInfoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkBeanRateIsRequired() throws Exception {
        int databaseSizeBeforeTest = userInfoRepository.findAll().size();
        // set the field null
        userInfo.setBeanRate(null);

        // Create the UserInfo, which fails.

        restUserInfoMockMvc.perform(post("/api/user-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userInfo)))
            .andExpect(status().isBadRequest());

        List<UserInfo> userInfoList = userInfoRepository.findAll();
        assertThat(userInfoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStateIsRequired() throws Exception {
        int databaseSizeBeforeTest = userInfoRepository.findAll().size();
        // set the field null
        userInfo.setState(null);

        // Create the UserInfo, which fails.

        restUserInfoMockMvc.perform(post("/api/user-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userInfo)))
            .andExpect(status().isBadRequest());

        List<UserInfo> userInfoList = userInfoRepository.findAll();
        assertThat(userInfoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllUserInfos() throws Exception {
        // Initialize the database
        userInfoRepository.saveAndFlush(userInfo);

        // Get all the userInfoList
        restUserInfoMockMvc.perform(get("/api/user-infos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(userInfo.getId().intValue())))
            .andExpect(jsonPath("$.[*].userName").value(hasItem(DEFAULT_USER_NAME.toString())))
            .andExpect(jsonPath("$.[*].nickName").value(hasItem(DEFAULT_NICK_NAME.toString())))
            .andExpect(jsonPath("$.[*].phoneNumber").value(hasItem(DEFAULT_PHONE_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].qq").value(hasItem(DEFAULT_QQ.toString())))
            .andExpect(jsonPath("$.[*].weiChat").value(hasItem(DEFAULT_WEI_CHAT.toString())))
            .andExpect(jsonPath("$.[*].starId").value(hasItem(DEFAULT_STAR_ID.toString())))
            .andExpect(jsonPath("$.[*].regDate").value(hasItem(DEFAULT_REG_DATE.toString())))
            .andExpect(jsonPath("$.[*].loginName").value(hasItem(DEFAULT_LOGIN_NAME.toString())))
            .andExpect(jsonPath("$.[*].password").value(hasItem(DEFAULT_PASSWORD.toString())))
            .andExpect(jsonPath("$.[*].cookie").value(hasItem(DEFAULT_COOKIE.toString())))
            .andExpect(jsonPath("$.[*].timeRate").value(hasItem(DEFAULT_TIME_RATE.doubleValue())))
            .andExpect(jsonPath("$.[*].beanRate").value(hasItem(DEFAULT_BEAN_RATE.doubleValue())))
            .andExpect(jsonPath("$.[*].lastMaintain").value(hasItem(DEFAULT_LAST_MAINTAIN.toString())))
            .andExpect(jsonPath("$.[*].state").value(hasItem(DEFAULT_STATE.toString())));
    }

    @Test
    @Transactional
    public void getUserInfo() throws Exception {
        // Initialize the database
        userInfoRepository.saveAndFlush(userInfo);

        // Get the userInfo
        restUserInfoMockMvc.perform(get("/api/user-infos/{id}", userInfo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(userInfo.getId().intValue()))
            .andExpect(jsonPath("$.userName").value(DEFAULT_USER_NAME.toString()))
            .andExpect(jsonPath("$.nickName").value(DEFAULT_NICK_NAME.toString()))
            .andExpect(jsonPath("$.phoneNumber").value(DEFAULT_PHONE_NUMBER.toString()))
            .andExpect(jsonPath("$.qq").value(DEFAULT_QQ.toString()))
            .andExpect(jsonPath("$.weiChat").value(DEFAULT_WEI_CHAT.toString()))
            .andExpect(jsonPath("$.starId").value(DEFAULT_STAR_ID.toString()))
            .andExpect(jsonPath("$.regDate").value(DEFAULT_REG_DATE.toString()))
            .andExpect(jsonPath("$.loginName").value(DEFAULT_LOGIN_NAME.toString()))
            .andExpect(jsonPath("$.password").value(DEFAULT_PASSWORD.toString()))
            .andExpect(jsonPath("$.cookie").value(DEFAULT_COOKIE.toString()))
            .andExpect(jsonPath("$.timeRate").value(DEFAULT_TIME_RATE.doubleValue()))
            .andExpect(jsonPath("$.beanRate").value(DEFAULT_BEAN_RATE.doubleValue()))
            .andExpect(jsonPath("$.lastMaintain").value(DEFAULT_LAST_MAINTAIN.toString()))
            .andExpect(jsonPath("$.state").value(DEFAULT_STATE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingUserInfo() throws Exception {
        // Get the userInfo
        restUserInfoMockMvc.perform(get("/api/user-infos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUserInfo() throws Exception {
        // Initialize the database
        userInfoRepository.saveAndFlush(userInfo);
        int databaseSizeBeforeUpdate = userInfoRepository.findAll().size();

        // Update the userInfo
        UserInfo updatedUserInfo = userInfoRepository.findOne(userInfo.getId());
        updatedUserInfo
            .userName(UPDATED_USER_NAME)
            .nickName(UPDATED_NICK_NAME)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .qq(UPDATED_QQ)
            .weiChat(UPDATED_WEI_CHAT)
            .starId(UPDATED_STAR_ID)
            .regDate(UPDATED_REG_DATE)
            .loginName(UPDATED_LOGIN_NAME)
            .password(UPDATED_PASSWORD)
            .cookie(UPDATED_COOKIE)
            .timeRate(UPDATED_TIME_RATE)
            .beanRate(UPDATED_BEAN_RATE)
            .lastMaintain(UPDATED_LAST_MAINTAIN)
            .state(UPDATED_STATE);

        restUserInfoMockMvc.perform(put("/api/user-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedUserInfo)))
            .andExpect(status().isOk());

        // Validate the UserInfo in the database
        List<UserInfo> userInfoList = userInfoRepository.findAll();
        assertThat(userInfoList).hasSize(databaseSizeBeforeUpdate);
        UserInfo testUserInfo = userInfoList.get(userInfoList.size() - 1);
        assertThat(testUserInfo.getUserName()).isEqualTo(UPDATED_USER_NAME);
        assertThat(testUserInfo.getNickName()).isEqualTo(UPDATED_NICK_NAME);
        assertThat(testUserInfo.getPhoneNumber()).isEqualTo(UPDATED_PHONE_NUMBER);
        assertThat(testUserInfo.getQq()).isEqualTo(UPDATED_QQ);
        assertThat(testUserInfo.getWeiChat()).isEqualTo(UPDATED_WEI_CHAT);
        assertThat(testUserInfo.getStarId()).isEqualTo(UPDATED_STAR_ID);
        assertThat(testUserInfo.getRegDate()).isEqualTo(UPDATED_REG_DATE);
        assertThat(testUserInfo.getLoginName()).isEqualTo(UPDATED_LOGIN_NAME);
        assertThat(testUserInfo.getPassword()).isEqualTo(UPDATED_PASSWORD);
        assertThat(testUserInfo.getCookie()).isEqualTo(UPDATED_COOKIE);
        assertThat(testUserInfo.getTimeRate()).isEqualTo(UPDATED_TIME_RATE);
        assertThat(testUserInfo.getBeanRate()).isEqualTo(UPDATED_BEAN_RATE);
        assertThat(testUserInfo.getLastMaintain()).isEqualTo(UPDATED_LAST_MAINTAIN);
        assertThat(testUserInfo.getState()).isEqualTo(UPDATED_STATE);
    }

    @Test
    @Transactional
    public void updateNonExistingUserInfo() throws Exception {
        int databaseSizeBeforeUpdate = userInfoRepository.findAll().size();

        // Create the UserInfo

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restUserInfoMockMvc.perform(put("/api/user-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userInfo)))
            .andExpect(status().isCreated());

        // Validate the UserInfo in the database
        List<UserInfo> userInfoList = userInfoRepository.findAll();
        assertThat(userInfoList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteUserInfo() throws Exception {
        // Initialize the database
        userInfoRepository.saveAndFlush(userInfo);
        int databaseSizeBeforeDelete = userInfoRepository.findAll().size();

        // Get the userInfo
        restUserInfoMockMvc.perform(delete("/api/user-infos/{id}", userInfo.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<UserInfo> userInfoList = userInfoRepository.findAll();
        assertThat(userInfoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserInfo.class);
        UserInfo userInfo1 = new UserInfo();
        userInfo1.setId(1L);
        UserInfo userInfo2 = new UserInfo();
        userInfo2.setId(userInfo1.getId());
        assertThat(userInfo1).isEqualTo(userInfo2);
        userInfo2.setId(2L);
        assertThat(userInfo1).isNotEqualTo(userInfo2);
        userInfo1.setId(null);
        assertThat(userInfo1).isNotEqualTo(userInfo2);
    }
}
