package com.flotting.api.history.service;

import com.flotting.api.history.entity.AutoRecommendHistory;
import com.flotting.api.history.model.AutoRecommendedData;
import com.flotting.api.user.SampleDataMaker;
import com.flotting.api.user.entity.UserDetailEntity;
import com.flotting.api.user.entity.UserSimpleEntity;
import com.flotting.api.user.model.UserResponseDto;
import com.flotting.api.user.model.UserSimpleResponseDto;
import com.flotting.api.user.service.UserService;
import com.flotting.api.util.service.ExcelService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
class AutoRecommendServiceTest extends SampleDataMaker {

    @Autowired
    private AutoRecommendService autoRecommendService;
    @Autowired
    private UserService userService;

    @Autowired
    private ExcelService excelService;

    List<String> maleGData = Arrays.asList(
            "김민준,42,166,남성,서울 북부,48392047,1048392047,프립,,,,,,,,,별빛여행자,일반(중견기업),개발자 - 유비케어,대학교 졸업,N,자주 마심,G,2024-01-01,애교있는,산책,활성,930520",
            "김지안,42,171,남성,서울 동부,19283745,1019283745,소모임,,ISFP,,,,,,,향기로운순간,일반(중소기업),아트디렉터 - Jarvis Products Corporation,대학교 졸업,N,거의 안 마심,G,2024-01-02,애교있는,산책,활성,920429",
            "이하윤,41,172,남성,서울 북부,19283746,1019283746,프립,,,,,,,,,초록바람,일반(공기업/공무원),산림청 국립산림과학원 국제협력 업무,대학교 졸업,N,자주 마심,G,2024-01-04,애교있는,산책,활성,910308",
            "이태윤,41,182,남성,서울 동부,74839215,1074839215,소모임,,ISFP,,,,,,,빛나는오로라,금융직,직장인 - 코스콤,대학교 졸업,N,거의 안 마심,G,2024-01-05,애교있는,산책,활성,901217"
    );

    List<String> malePData = Arrays.asList(
            "윤도현,37,169,남성,서울 북부,29384756,1029384756,프립,,,,,,,,,비밀의숲,의료직(간호사 등),대학병원 간호직,대학교 졸업,N,자주 마심,P,2024-01-16,애교있는,산책,활성,991023",
            "송지아,36,175,남성,서울 남부,83746529,1083746529,프립,,,,,,,,,시간을달리다,일반(공기업/공무원),직장인 - 과기부 산하 소프트웨어정책연구소,대학교 졸업,N,자주 마심,P,2024-01-19,애교있는,산책,활성,981128",
            "강하늘,35,181,남성,서울 남부,10293847,1010293847,프립,,ENTP,,,,,,,빛나는별하나,일반(공기업/공무원),정부서울청사 청원경찰직(준공무원)으로 근무중입니다. 정식 공무원은 아니지만 신분이 보장되고 호봉제라 공무원이나 사실상 다를 바 없습니다.,대학교 졸업,N,자주 마심,P,2024-01-22,애교있는,산책,활성,970901",
            "조민서,34,167,남성,서울 남부,56473829,1056473829,프립,,ESTJ,,,,,,,파도소리,일반(중소기업),이태리 주방 브랜팀장,대학교 졸업,N,자주 마심,P,2024-03-17,애교있는,산책,활성,961006"
    );

    List<String> maleDData = Arrays.asList(
            "박서연,40,165,남성,서울 북부,37482910,1037482910,프립,,,,,,,,,푸른하늘꿈,교육직,초등교사,대학교 졸업,N,자주 마심,D,2024-01-07,애교있는,산책,활성,891126",
            "최준호,39,184,남성,서울 북부,58293746,1058293746,프립,,,,,,,,,달콤한소나타,일반(대기업),CJ대한통운 AI빅데이터연구,대학교 졸업,N,자주 마심,D,2024-01-10,애교있는,산책,활성,881004",
            "정다은,38,178,남성,서울 북부,74839201,1074839201,프립,,,,,,,,,꿈꾸는토끼,일반(중소기업),코운디티웹개발,대학교 졸업,N,자주 마심,D,2024-01-13,애교있는,산책,활성,000114",
            "박지후,30,176,남성,서울 남부,38475629,1038475629,프립,,ESTJ,,,,,,,햇살속으로,일반(스타트업),직장인-it 회사,고등학교 졸업,N,자주 마심,D,2024-03-21,애교있는,산책,활성,960824"
    );

    List<String> femaleGData = Arrays.asList(
            "정민교,38,156,여성,경기 남부,29381045,1029381045,와디즈,,ISTP,,,,,,,햇살속의춤,일반(중소기업),크리스챤 디올,고등학교 졸업,Y,가끔 마심,G,2024-03-21,애교있는,산책,활성,841005",
            "조하민,34,170,여성,서울 서부,74829108,1074829108,네이버 스토어,,INFJ,,,,,,,해질녘빛깔,일반(중견기업),직장인 - 에스앤아이코퍼레이션,대학교 졸업,N,아예 안 마심,G,2024-01-25,애교있는,산책,활성,920303",
            "조민규,34,162,여성,경기 서부,83729100,1083729100,지인 추천,김광태,ISFJ,,,,,,,별이빛나는밤에,일반(공기업/공무원),한국지역난방공사,대학교 졸업,Y,가끔 마심,G,2024-01-26,애교있는,산책,활성,910207",
            "한유주,33,152,여성,서울 서부,38475623,1038475623,네이버 스토어,,INFJ,,,,,,,밤하늘별빛,일반(중소기업),제이앤코화장생활용품 브랜드,대학교 졸업,N,아예 안 마심,G,2024-01-27,애교있는,산책,활성,900116"
    );

    List<String> femalePData = Arrays.asList(
            "송하율,36,167,여성,서울 동부,84736295,1084736295,네이버 스토어,,INFJ,,,,,,,푸른바다의전설,일반(공기업/공무원),분당서울대학교병원(공공기관) 행정직,대학교 졸업,N,아예 안 마심,P,2024-01-20,애교있는,산책,활성,950702",
            "강연우,35,160,여성,서울 서부,29381042,1029381042,네이버 스토어,,INFJ,,,,,,,달콤커피,일반(공기업/공무원),교통안전공단,고등학교 졸업,N,아예 안 마심,P,2024-01-23,애교있는,산책,활성,940610",
            "강서현,35,168,여성,경기 동부,19238475,1019238475,와디즈,,ISFJ,,,,,,,눈부신날에,일반(공기업/공무원),KATRI시험연구원(한국의류시험연구원),대학교 졸업,Y,가끔 마심,P,2024-01-24,애교있는,산책,활성,930420",
            "송유진,36,171,여성,경기 동부,38475620,1038475620,와디즈,,ISFJ,,,,,,,꽃향기속으로,일반(중소기업),사회복지사,대학교 졸업,Y,가끔 마심,P,2024-01-21,애교있는,산책,활성,940515"
        );

    List<String> femaleDData = Arrays.asList(
            "윤지영,37,153,여성,서울 동부,56473821,1056473821,네이버 스토어,,ISFP,,,,,,,무지개빛꿈,일반(공기업/공무원),주택도시보증공사,대학교 졸업,N,거의 안 마심,D,2024-03-22,애교있는,산책,활성,830722",
            "이주안,31,158,여성,서울 서부,83729109,1083729109,네이버 스토어,,ESFP,,,,,,,눈부신아침,일반(중견기업),농협 경제지주 전문직,대학교 졸업,N,가끔 마심,D,2024-01-30,애교있는,산책,활성,840926",
            "강지우,25,168,여성,경기 북부,19283740,1019283740,네이버 스토어,,,,,,,,,푸른초원위에,의료직(간호사 등),간호사,대학교 졸업,N,가끔 마심,D,2024-03-15,애교있는,산책,활성,841208",
            "정다비,28,162,여성,경기 북부,10284738,1010284738,네이버 스토어,,ESFP,,,,,,,별빛아래서,일반(프리랜서),헤어디자이너,대학교 졸업,N,가끔 마심,D,2024-03-12,애교있는,산책,활성,860822"
    );


    @BeforeEach
    public void init() {
        userService.deleteAll();
    }

    @Test
    @Transactional
    public void autoRecommendTest() {
        //given
        makeUserData(true);

        //when
        //TODO 자동매칭알고리즘 테스트
        List<UserSimpleResponseDto> simpleUserInfos = userService.getSimpleUserInfos(Pageable.ofSize(20));
        UserSimpleResponseDto firstUser = simpleUserInfos.get(0);
        autoRecommendService.createAutoRecommend(firstUser.getUserNo());
        List<AutoRecommendHistory> result = autoRecommendService.getAll();

        //then
        Assertions.assertEquals(result.get(0).getReceiver().getUserNo(), firstUser.getUserNo());
        Assertions.assertEquals(result.get(1).getReceiver().getUserNo(), firstUser.getUserNo());
    }

    @Test
    @Transactional
    public void autoRecommendTestByCsv() {
        //given
        makeUserData(true);

        //when
        List<UserSimpleResponseDto> simpleUserInfos = userService.getSimpleUserInfos(Pageable.ofSize(20));
        UserSimpleResponseDto firstUser = simpleUserInfos.get(0);
        List<AutoRecommendedData> datas = autoRecommendService.createAutoRecommend(firstUser.getUserNo());

        //then
        MockHttpServletResponse response = new MockHttpServletResponse();
        excelService.downloadExcel(datas, response);
    }

    @Test
    @Transactional
    public void _2주이내_D등급_남성_사용자_상대방D등급_4명일때_매칭결과() {
        //given
        List<String> datas = new ArrayList<>();
        datas.add(maleDData.get(0));
        datas.addAll(femaleDData);
        List<UserResponseDto> sampleDatas = makeUserData(datas);
        List<String> expectedResult = Arrays.asList("윤지영", "이주안", "강지우", "정다비");

        //when
        List<AutoRecommendedData> result = autoRecommendService.createAutoRecommend("1037482910");
        List<String> recommendedUserNameList = getUserNameList(result);

        //then
        Assertions.assertEquals(result.size(), 4);
        Assertions.assertTrue(recommendedUserNameList.containsAll(expectedResult));
    }

    @Test
    @Transactional
    public void _2주이내_D등급_남성_사용자_상대방_D2명_P3명_매칭결과_D2_P2() {
        //given
        List<String> datas = new ArrayList<>();
        datas.add(maleDData.get(0));
        datas.add(femaleDData.get(0));
        datas.add(femaleDData.get(1));
        datas.add(femalePData.get(0));
        datas.add(femalePData.get(1));
        datas.add(femalePData.get(2));
        List<UserResponseDto> sampleDatas = makeUserData(datas);
        List<String> expectedResult = Arrays.asList("윤지영", "이주안", "강연우", "강서현");

        //when
        List<AutoRecommendedData> result = autoRecommendService.createAutoRecommend("1037482910");
        List<String> recommendedUserNameList = getUserNameList(result);

        //then
        Assertions.assertEquals(result.size(), 4);
        Assertions.assertTrue(recommendedUserNameList.containsAll(expectedResult));
    }

    @Test
    @Transactional
    public void _2주이내_D등급_남성_사용자_상대방_P4명_매칭결과_P4() {
        //given
        List<String> datas = new ArrayList<>();
        datas.add(maleDData.get(0));
        datas.addAll(femalePData);
        List<UserResponseDto> sampleDatas = makeUserData(datas);
        List<String> expectedResult = Arrays.asList("송하율", "강연우", "송유진", "강서현");

        //when
        List<AutoRecommendedData> result = autoRecommendService.createAutoRecommend("1037482910");
        List<String> recommendedUserNameList = getUserNameList(result);

        //then
        Assertions.assertEquals(result.size(), 4);
        Assertions.assertTrue(recommendedUserNameList.containsAll(expectedResult));
    }

    @Test
    @Transactional
    public void _2주이내_D등급_남성_사용자_상대방_P3명_G1명_매칭결과_P3_G1() {
        //given
        List<String> datas = new ArrayList<>();
        datas.add(maleDData.get(0));
        datas.add(femalePData.get(0));
        datas.add(femalePData.get(1));
        datas.add(femalePData.get(2));
        datas.add(femaleGData.get(0));
        List<UserResponseDto> sampleDatas = makeUserData(datas);
        List<String> expectedResult = Arrays.asList("송하율", "강연우", "강서현", "정민교");

        //when
        List<AutoRecommendedData> result = autoRecommendService.createAutoRecommend("1037482910");
        List<String> recommendedUserNameList = getUserNameList(result);

        //then
        Assertions.assertEquals(result.size(), 4);
        Assertions.assertTrue(recommendedUserNameList.containsAll(expectedResult));
    }

    @Test
    @Transactional
    public void _2주이내_D등급_남성_사용자_상대방_P1명_G3명_매칭결과_P1_G3() {
        //given
        List<String> datas = new ArrayList<>();
        datas.add(maleDData.get(0));
        datas.add(femalePData.get(0));
        datas.add(femaleGData.get(0));
        datas.add(femaleGData.get(1));
        datas.add(femaleGData.get(2));
        List<UserResponseDto> sampleDatas = makeUserData(datas);
        List<String> expectedResult = Arrays.asList("송하율", "정민교", "조하민", "조민규");

        //when
        List<AutoRecommendedData> result = autoRecommendService.createAutoRecommend("1037482910");
        List<String> recommendedUserNameList = getUserNameList(result);

        //then
        Assertions.assertEquals(result.size(), 4);
        Assertions.assertTrue(recommendedUserNameList.containsAll(expectedResult));
    }

    @Test
    @Transactional
    public void _2주이내_D등급_남성_사용자_상대방_G4명_매칭결과_G4() {
        //given
        List<String> datas = new ArrayList<>();
        datas.add(maleDData.get(0));
        datas.addAll(femaleGData);
        List<UserResponseDto> sampleDatas = makeUserData(datas);
        List<String> expectedResult = Arrays.asList("정민교", "조하민", "조민규", "한유주");

        //when
        List<AutoRecommendedData> result = autoRecommendService.createAutoRecommend("1037482910");
        List<String> recommendedUserNameList = getUserNameList(result);

        //then
        Assertions.assertEquals(result.size(), 4);
        Assertions.assertTrue(recommendedUserNameList.containsAll(expectedResult));
    }

    @Test
    @Transactional
    public void _2주이내_G등급_여성_사용자_상대방_P3_G2명_매칭결과_P2_G2() {
        //given
        List<String> datas = new ArrayList<>();
        datas.add(femaleGData.get(0));
        datas.add(malePData.get(0));
        datas.add(malePData.get(1));
        datas.add(malePData.get(2));
        datas.add(maleGData.get(0));
        datas.add(maleGData.get(1));
        List<UserResponseDto> sampleDatas = makeUserData(datas);
        List<String> expectedResult = Arrays.asList("김민준", "김지안", "송지아", "강하늘");

        //when
        List<AutoRecommendedData> result = autoRecommendService.createAutoRecommend("1029381045");
        List<String> recommendedUserNameList = getUserNameList(result);

        //then
        Assertions.assertEquals(result.size(), 4);
        Assertions.assertTrue(recommendedUserNameList.containsAll(expectedResult));
    }


    @Test
    @Transactional
    public void _2주이내_G등급_여성_사용자_상대방_P1_G3명_매칭결과_P1_G3() {
        //given
        List<String> datas = new ArrayList<>();
        datas.add(femaleGData.get(0));
        datas.add(malePData.get(0));
        datas.add(maleGData.get(0));
        datas.add(maleGData.get(1));
        datas.add(maleGData.get(2));
        List<UserResponseDto> sampleDatas = makeUserData(datas);
        List<String> expectedResult = Arrays.asList("윤도현", "김민준","김지안", "이하윤");

        //when
        List<AutoRecommendedData> result = autoRecommendService.createAutoRecommend("1029381045");
        List<String> recommendedUserNameList = getUserNameList(result);

        //then
        Assertions.assertEquals(result.size(), 4);
        Assertions.assertTrue(recommendedUserNameList.containsAll(expectedResult));
    }

    @Test
    @Transactional
    public void _2주이내_G등급_여성_사용자_상대방_G4명_매칭결과_G4() {
        //given
        List<String> datas = new ArrayList<>();
        datas.add(femaleGData.get(0));
        datas.addAll(maleGData);
        List<UserResponseDto> sampleDatas = makeUserData(datas);
        List<String> expectedResult = Arrays.asList("김민준","김지안", "이하윤", "이태윤");

        //when
        List<AutoRecommendedData> result = autoRecommendService.createAutoRecommend("1029381045");
        List<String> recommendedUserNameList = getUserNameList(result);

        //then
        Assertions.assertEquals(result.size(), 4);
        Assertions.assertTrue(recommendedUserNameList.containsAll(expectedResult));
    }

    @Test
    @Transactional
    public void _2주이내_P등급_여성_사용자_상대방_D3_P2명_매칭결과_D2_P2() {
        //given
        List<String> datas = new ArrayList<>();
        datas.add(femalePData.get(0));
        datas.add(maleDData.get(0));
        datas.add(maleDData.get(1));
        datas.add(maleDData.get(2));
        datas.add(malePData.get(0));
        datas.add(malePData.get(1));
        List<UserResponseDto> sampleDatas = makeUserData(datas);
        List<String> expectedResult = Arrays.asList("박서연", "정다은", "윤도현", "송지아");

        //when
        List<AutoRecommendedData> result = autoRecommendService.createAutoRecommend("1084736295");
        List<String> recommendedUserNameList = getUserNameList(result);

        //then
        Assertions.assertEquals(result.size(), 4);
        Assertions.assertTrue(recommendedUserNameList.containsAll(expectedResult));
    }

    @Test
    @Transactional
    public void _2주이내_P등급_여성_사용자_상대방_D1_P3명_매칭결과_D1_P3() {
        //given
        List<String> datas = new ArrayList<>();
        datas.add(femalePData.get(0));
        datas.add(maleDData.get(0));
        datas.add(malePData.get(0));
        datas.add(malePData.get(1));
        datas.add(malePData.get(2));
        List<UserResponseDto> sampleDatas = makeUserData(datas);
        List<String> expectedResult = Arrays.asList("박서연", "윤도현", "송지아", "강하늘");

        //when
        List<AutoRecommendedData> result = autoRecommendService.createAutoRecommend("1084736295");
        List<String> recommendedUserNameList = getUserNameList(result);

        //then
        Assertions.assertEquals(result.size(), 4);
        Assertions.assertTrue(recommendedUserNameList.containsAll(expectedResult));
    }

    @Test
    @Transactional
    public void _2주이내_P등급_여성_사용자_상대방_P4명_매칭결과_P4() {
        //given
        List<String> datas = new ArrayList<>();
        datas.add(femalePData.get(0));
        datas.addAll(malePData);
        List<UserResponseDto> sampleDatas = makeUserData(datas);
        List<String> expectedResult = Arrays.asList("윤도현", "송지아", "강하늘", "조민서");

        //when
        List<AutoRecommendedData> result = autoRecommendService.createAutoRecommend("1084736295");
        List<String> recommendedUserNameList = getUserNameList(result);

        //then
        Assertions.assertEquals(result.size(), 4);
        Assertions.assertTrue(recommendedUserNameList.containsAll(expectedResult));
    }

    @Test
    @Transactional
    public void _2주이내_D등급_여성_사용자_상대방_D4명_매칭결과_D4() {
        //given
        List<String> datas = new ArrayList<>();
        datas.add(femaleDData.get(0));
        datas.addAll(maleDData);
        List<UserResponseDto> sampleDatas = makeUserData(datas);
        List<String> expectedResult = Arrays.asList("박서연", "최준호", "정다은", "박지후");

        //when
        List<AutoRecommendedData> result = autoRecommendService.createAutoRecommend("1056473821");
        List<String> recommendedUserNameList = getUserNameList(result);

        //then
        Assertions.assertEquals(result.size(), 4);
        Assertions.assertTrue(recommendedUserNameList.containsAll(expectedResult));
    }

    @Test
    @Transactional
    public void _2주이내_D등급_여성_사용자_상대방_D1_P3명_매칭결과_D1_P3() {
        //given
        List<String> datas = new ArrayList<>();
        datas.add(femaleDData.get(0));
        datas.add(maleDData.get(0));
        datas.add(malePData.get(0));
        datas.add(malePData.get(1));
        datas.add(malePData.get(2));
        List<UserResponseDto> sampleDatas = makeUserData(datas);
        List<String> expectedResult = Arrays.asList("박서연", "윤도현", "송지아", "강하늘");

        //when
        List<AutoRecommendedData> result = autoRecommendService.createAutoRecommend("1056473821");
        List<String> recommendedUserNameList = getUserNameList(result);

        //then
        Assertions.assertEquals(result.size(), 4);
        Assertions.assertTrue(recommendedUserNameList.containsAll(expectedResult));
    }

    @Test
    @Transactional
    public void _2주이내_D등급_여성_사용자_상대방_P4명_매칭결과_P4() {
        //given
        List<String> datas = new ArrayList<>();
        datas.add(femaleDData.get(0));
        datas.addAll(malePData);
        List<UserResponseDto> sampleDatas = makeUserData(datas);
        List<String> expectedResult = Arrays.asList("윤도현", "송지아", "강하늘", "조민서");

        //when
        List<AutoRecommendedData> result = autoRecommendService.createAutoRecommend("1056473821");
        List<String> recommendedUserNameList = getUserNameList(result);

        //then
        Assertions.assertEquals(result.size(), 4);
        Assertions.assertTrue(recommendedUserNameList.containsAll(expectedResult));
    }


    @Test
    @Transactional
    public void _2주이내_D등급_여성_사용자_상대방_P3_G1명_매칭결과_P3_G1() {
        //given
        List<String> datas = new ArrayList<>();
        datas.add(femaleDData.get(0));
        datas.add(malePData.get(0));
        datas.add(malePData.get(1));
        datas.add(malePData.get(2));
        datas.add(maleGData.get(0));
        List<UserResponseDto> sampleDatas = makeUserData(datas);
        List<String> expectedResult = Arrays.asList("윤도현", "송지아", "강하늘", "김민준");

        //when
        List<AutoRecommendedData> result = autoRecommendService.createAutoRecommend("1056473821");
        List<String> recommendedUserNameList = getUserNameList(result);

        //then
        Assertions.assertEquals(result.size(), 4);
        Assertions.assertTrue(recommendedUserNameList.containsAll(expectedResult));
    }

    @Test
    @Transactional
    public void _2주이내_D등급_여성_사용자_상대방_P3_G2명_매칭결과_P3_G1() {
        //given
        List<String> datas = new ArrayList<>();
        datas.add(femaleDData.get(0));
        datas.add(malePData.get(0));
        datas.add(malePData.get(1));
        datas.add(malePData.get(2));
        datas.add(maleGData.get(0));
        datas.add(maleGData.get(1));
        List<UserResponseDto> sampleDatas = makeUserData(datas);
        List<String> expectedResult = Arrays.asList("윤도현", "송지아","강하늘", "김지안");

        //when
        List<AutoRecommendedData> result = autoRecommendService.createAutoRecommend("1056473821");
        List<String> recommendedUserNameList = getUserNameList(result);

        //then
        Assertions.assertEquals(result.size(), 4);
        Assertions.assertTrue(recommendedUserNameList.containsAll(expectedResult));
    }

    @Test
    @Transactional
    public void _2주이내_D등급_여성_사용자_상대방_P1_G3명_매칭결과_P1_G3() {
        //given
        List<String> datas = new ArrayList<>();
        datas.add(femaleDData.get(0));
        datas.add(malePData.get(0));
        datas.add(maleGData.get(0));
        datas.add(maleGData.get(1));
        datas.add(maleGData.get(2));
        List<UserResponseDto> sampleDatas = makeUserData(datas);
        List<String> expectedResult = Arrays.asList("윤도현","김민준", "김지안", "이하윤");

        //when
        List<AutoRecommendedData> result = autoRecommendService.createAutoRecommend("1056473821");
        List<String> recommendedUserNameList = getUserNameList(result);

        //then
        Assertions.assertEquals(result.size(), 4);
        Assertions.assertTrue(recommendedUserNameList.containsAll(expectedResult));
    }

    @Test
    @Transactional
    public void _2주이내_D등급_여성_사용자_상대방_G4명_매칭결과_G4() {
        //given
        List<String> datas = new ArrayList<>();
        datas.add(femaleDData.get(0));
        datas.addAll(maleGData);
        List<UserResponseDto> sampleDatas = makeUserData(datas);
        List<String> expectedResult = Arrays.asList("김민준", "김지안", "이하윤", "이태윤");

        //when
        List<AutoRecommendedData> result = autoRecommendService.createAutoRecommend("1056473821");
        List<String> recommendedUserNameList = getUserNameList(result);

        //then
        Assertions.assertEquals(result.size(), 4);
        Assertions.assertTrue(recommendedUserNameList.containsAll(expectedResult));
    }

    @Test
    @Transactional
    public void _2주이상_G등급_남성_사용자_상대방_G3명_매칭결과_G2() {
        //given
        String targetUserPhoneNumber = "1048392047";
        List<String> datas = new ArrayList<>();
        datas.add(maleGData.get(0));
        datas.add(femaleGData.get(0));
        datas.add(femaleGData.get(1));
        datas.add(femaleGData.get(2));
        List<UserResponseDto> sampleDatas = makeUserData(datas);
        userService.updateApprovedAt(userService.getUserByPhoneNumber(targetUserPhoneNumber).getUserNo(), LocalDateTime.now().minusWeeks(4));
        List<String> expectedResult = Arrays.asList("조하민", "조민규");

        //when
        List<AutoRecommendedData> result = autoRecommendService.createAutoRecommend(targetUserPhoneNumber);
        List<String> recommendedUserNameList = getUserNameList(result);

        //then
        Assertions.assertEquals(result.size(), 2);
        Assertions.assertTrue(recommendedUserNameList.containsAll(expectedResult));
    }

    @Test
    @Transactional
    public void _2주이상_P등급_남성_사용자_상대방_P3명_매칭결과_P2() {
        //given
        String targetUserPhoneNumber = "1029384756";
        List<String> datas = new ArrayList<>();
        datas.add(malePData.get(0));
        datas.add(femalePData.get(0));
        datas.add(femalePData.get(1));
        datas.add(femalePData.get(2));
        List<UserResponseDto> sampleDatas = makeUserData(datas);
        userService.updateApprovedAt(userService.getUserByPhoneNumber(targetUserPhoneNumber).getUserNo(), LocalDateTime.now().minusWeeks(4));
        List<String> expectedResult = Arrays.asList("송하율", "강연우");

        //when
        List<AutoRecommendedData> result = autoRecommendService.createAutoRecommend(targetUserPhoneNumber);
        List<String> recommendedUserNameList = getUserNameList(result);

        //then
        Assertions.assertEquals(result.size(), 2);
        Assertions.assertTrue(recommendedUserNameList.containsAll(expectedResult));
    }

    @Test
    @Transactional
    public void _2주이상_P등급_남성_사용자_상대방_P1_G1명_매칭결과_P1_G1() {
        //given
        String targetUserPhoneNumber = "1029384756";
        List<String> datas = new ArrayList<>();
        datas.add(malePData.get(0));
        datas.add(femalePData.get(0));
        datas.add(femaleGData.get(0));
        List<UserResponseDto> sampleDatas = makeUserData(datas);
        userService.updateApprovedAt(userService.getUserByPhoneNumber(targetUserPhoneNumber).getUserNo(), LocalDateTime.now().minusWeeks(4));
        List<String> expectedResult = Arrays.asList("송하율", "정민교");

        //when
        List<AutoRecommendedData> result = autoRecommendService.createAutoRecommend(targetUserPhoneNumber);
        List<String> recommendedUserNameList = getUserNameList(result);

        //then
        Assertions.assertEquals(result.size(), 2);
        Assertions.assertTrue(recommendedUserNameList.containsAll(expectedResult));
    }

    @Test
    @Transactional
    public void _2주이상_P등급_남성_사용자_상대방_G2명_매칭결과_G2() {
        //given
        String targetUserPhoneNumber = "1029384756";
        List<String> datas = new ArrayList<>();
        datas.add(malePData.get(0));
        datas.add(femaleGData.get(0));
        datas.add(femaleGData.get(1));
        List<UserResponseDto> sampleDatas = makeUserData(datas);
        userService.updateApprovedAt(userService.getUserByPhoneNumber(targetUserPhoneNumber).getUserNo(), LocalDateTime.now().minusWeeks(4));
        List<String> expectedResult = Arrays.asList("정민교", "조하민");

        //when
        List<AutoRecommendedData> result = autoRecommendService.createAutoRecommend(targetUserPhoneNumber);
        List<String> recommendedUserNameList = getUserNameList(result);

        //then
        Assertions.assertEquals(result.size(), 2);
        Assertions.assertTrue(recommendedUserNameList.containsAll(expectedResult));
    }

    @Test
    @Transactional
    public void _2주이상_D등급_남성_사용자_상대방_D3명_매칭결과_D2() {
        //given
        String targetUserPhoneNumber = "1037482910";
        List<String> datas = new ArrayList<>();
        datas.add(maleDData.get(0));
        datas.add(femaleDData.get(0));
        datas.add(femaleDData.get(1));
        datas.add(femaleDData.get(3));
        List<UserResponseDto> sampleDatas = makeUserData(datas);
        userService.updateApprovedAt(userService.getUserByPhoneNumber(targetUserPhoneNumber).getUserNo(), LocalDateTime.now().minusWeeks(4));
        List<String> expectedResult = Arrays.asList("이주안", "정다비");

        //when
        List<AutoRecommendedData> result = autoRecommendService.createAutoRecommend(targetUserPhoneNumber);
        List<String> recommendedUserNameList = getUserNameList(result);

        //then
        Assertions.assertEquals(result.size(), 2);
        Assertions.assertTrue(recommendedUserNameList.containsAll(expectedResult));
    }

    @Test
    @Transactional
    public void _2주이상_D등급_남성_사용자_상대방_P1_D1명_매칭결과_P1_D1() {
        //given
        String targetUserPhoneNumber = "1037482910";
        List<String> datas = new ArrayList<>();
        datas.add(maleDData.get(0));
        datas.add(femalePData.get(0));
        datas.add(femaleDData.get(0));
        List<UserResponseDto> sampleDatas = makeUserData(datas);
        userService.updateApprovedAt(userService.getUserByPhoneNumber(targetUserPhoneNumber).getUserNo(), LocalDateTime.now().minusWeeks(4));
        List<String> expectedResult = Arrays.asList("송하율", "윤지");

        //when
        List<AutoRecommendedData> result = autoRecommendService.createAutoRecommend(targetUserPhoneNumber);
        List<String> recommendedUserNameList = getUserNameList(result);

        //then
        Assertions.assertEquals(result.size(), 2);
        Assertions.assertTrue(recommendedUserNameList.containsAll(expectedResult));
    }


    @Test
    @Transactional
    public void _2주이상_D등급_남성_사용자_상대방_P2명_매칭결과_P2() {
        //given
        String targetUserPhoneNumber = "1037482910";
        List<String> datas = new ArrayList<>();
        datas.add(maleDData.get(0));
        datas.add(femalePData.get(0));
        datas.add(femalePData.get(1));
        List<UserResponseDto> sampleDatas = makeUserData(datas);
        userService.updateApprovedAt(userService.getUserByPhoneNumber(targetUserPhoneNumber).getUserNo(), LocalDateTime.now().minusWeeks(4));
        List<String> expectedResult = Arrays.asList("송하율", "강연우");

        //when
        List<AutoRecommendedData> result = autoRecommendService.createAutoRecommend(targetUserPhoneNumber);
        List<String> recommendedUserNameList = getUserNameList(result);

        //then
        Assertions.assertEquals(result.size(), 2);
        Assertions.assertTrue(recommendedUserNameList.containsAll(expectedResult));
    }

    @Test
    @Transactional
    public void _2주이상_D등급_남성_사용자_상대방_P1_G1명_매칭결과_P1_G1() {
        //given
        String targetUserPhoneNumber = "1037482910";
        List<String> datas = new ArrayList<>();
        datas.add(maleDData.get(0));
        datas.add(femalePData.get(0));
        datas.add(femaleGData.get(0));
        List<UserResponseDto> sampleDatas = makeUserData(datas);
        userService.updateApprovedAt(userService.getUserByPhoneNumber(targetUserPhoneNumber).getUserNo(), LocalDateTime.now().minusWeeks(4));
        List<String> expectedResult = Arrays.asList("송하율", "정민교");

        //when
        List<AutoRecommendedData> result = autoRecommendService.createAutoRecommend(targetUserPhoneNumber);
        List<String> recommendedUserNameList = getUserNameList(result);

        //then
        Assertions.assertEquals(result.size(), 2);
        Assertions.assertTrue(recommendedUserNameList.containsAll(expectedResult));
    }

    @Test
    @Transactional
    public void _2주이상_D등급_남성_사용자_상대방_D1_G2명_매칭결과_D1_G1() {
        //given
        String targetUserPhoneNumber ="1037482910";
        List<String> datas = new ArrayList<>();
        datas.add(maleDData.get(0));
        datas.add(femaleDData.get(0));
        datas.add(femaleGData.get(0));
        datas.add(femaleGData.get(1));
        List<UserResponseDto> sampleDatas = makeUserData(datas);
        userService.updateApprovedAt(userService.getUserByPhoneNumber(targetUserPhoneNumber).getUserNo(), LocalDateTime.now().minusWeeks(4));
        List<String> expectedResult = Arrays.asList("윤지영", "조하민");

        //when
        List<AutoRecommendedData> result = autoRecommendService.createAutoRecommend(targetUserPhoneNumber);
        List<String> recommendedUserNameList = getUserNameList(result);

        //then
        Assertions.assertEquals(result.size(), 2);
        Assertions.assertTrue(recommendedUserNameList.containsAll(expectedResult));
    }

    @Test
    @Transactional
    public void _2주이상_G등급_여성_사용자_상대방_G3명_매칭결과_G2() {
        //given
        String targetUserPhoneNumber = "1029381045";
        List<String> datas = new ArrayList<>();
        datas.add(femaleGData.get(0));
        datas.add(maleGData.get(0));
        datas.add(maleGData.get(1));
        datas.add(maleGData.get(2));
        List<UserResponseDto> sampleDatas = makeUserData(datas);
        userService.updateApprovedAt(userService.getUserByPhoneNumber(targetUserPhoneNumber).getUserNo(), LocalDateTime.now().minusWeeks(4));
        List<String> expectedResult = Arrays.asList("이하윤", "김지안");

        //when
        List<AutoRecommendedData> result = autoRecommendService.createAutoRecommend(targetUserPhoneNumber);
        List<String> recommendedUserNameList = getUserNameList(result);

        //then
        Assertions.assertEquals(result.size(), 2);
        Assertions.assertTrue(recommendedUserNameList.containsAll(expectedResult));
    }

    @Test
    @Transactional
    public void _2주이상_P등급_여성_사용자_상대방_P3명_매칭결과_P2() {
        //given
        String targetUserPhoneNumber = "1084736295";
        List<String> datas = new ArrayList<>();
        datas.add(femalePData.get(0));
        datas.add(malePData.get(0));
        datas.add(malePData.get(1));
        datas.add(malePData.get(2));
        List<UserResponseDto> sampleDatas = makeUserData(datas);
        userService.updateApprovedAt(userService.getUserByPhoneNumber(targetUserPhoneNumber).getUserNo(), LocalDateTime.now().minusWeeks(4));
        List<String> expectedResult = Arrays.asList("송지아" , "강하늘");

        //when
        List<AutoRecommendedData> result = autoRecommendService.createAutoRecommend(targetUserPhoneNumber);
        List<String> recommendedUserNameList = getUserNameList(result);

        //then
        Assertions.assertEquals(result.size(), 2);
        Assertions.assertTrue(recommendedUserNameList.containsAll(expectedResult));
    }


    @Test
    @Transactional
    public void _2주이상_P등급_여성_사용자_상대방_P1_G1명_매칭결과_P1_G1() {
        //given
        String targetUserPhoneNumber = "1084736295";
        List<String> datas = new ArrayList<>();
        datas.add(femalePData.get(0));
        datas.add(malePData.get(0));
        datas.add(maleGData.get(0));
        List<UserResponseDto> sampleDatas = makeUserData(datas);
        userService.updateApprovedAt(userService.getUserByPhoneNumber(targetUserPhoneNumber).getUserNo(), LocalDateTime.now().minusWeeks(4));
        List<String> expectedResult = Arrays.asList("윤도현", "김민준");

        //when
        List<AutoRecommendedData> result = autoRecommendService.createAutoRecommend(targetUserPhoneNumber);
        List<String> recommendedUserNameList = getUserNameList(result);

        //then
        Assertions.assertEquals(result.size(), 2);
        Assertions.assertTrue(recommendedUserNameList.containsAll(expectedResult));
    }

    @Test
    @Transactional
    public void _2주이상_P등급_여성_사용자_상대방_G2명_매칭결과_G2() {
        //given
        String targetUserPhoneNumber = "1084736295";
        List<String> datas = new ArrayList<>();
        datas.add(femalePData.get(0));
        datas.add(maleGData.get(0));
        datas.add(maleGData.get(1));
        List<UserResponseDto> sampleDatas = makeUserData(datas);
        userService.updateApprovedAt(userService.getUserByPhoneNumber(targetUserPhoneNumber).getUserNo(), LocalDateTime.now().minusWeeks(4));
        List<String> expectedResult = Arrays.asList("김민준", "김지안");

        //when
        List<AutoRecommendedData> result = autoRecommendService.createAutoRecommend(targetUserPhoneNumber);
        List<String> recommendedUserNameList = getUserNameList(result);

        //then
        Assertions.assertEquals(result.size(), 2);
        Assertions.assertTrue(recommendedUserNameList.containsAll(expectedResult));
    }

    @Test
    @Transactional
    public void _2주이상_D등급_여성_사용자_상대방_D3명_매칭결과_D2() {
        //given
        String targetUserPhoneNumber = "1056473821";
        List<String> datas = new ArrayList<>();
        datas.add(femaleDData.get(0));
        datas.add(maleDData.get(0));
        datas.add(maleDData.get(1));
        datas.add(maleDData.get(2));
        List<UserResponseDto> sampleDatas = makeUserData(datas);
        userService.updateApprovedAt(userService.getUserByPhoneNumber(targetUserPhoneNumber).getUserNo(), LocalDateTime.now().minusWeeks(4));
        List<String> expectedResult = Arrays.asList("박서연", "최준호");

        //when
        List<AutoRecommendedData> result = autoRecommendService.createAutoRecommend(targetUserPhoneNumber);
        List<String> recommendedUserNameList = getUserNameList(result);

        //then
        Assertions.assertEquals(result.size(), 2);
        Assertions.assertTrue(recommendedUserNameList.containsAll(expectedResult));
    }

    @Test
    @Transactional
    public void _2주이상_D등급_여성_사용자_상대방_P1_D1명_매칭결과_P1_D1() {
        //given
        String targetUserPhoneNumber = "1056473821";
        List<String> datas = new ArrayList<>();
        datas.add(femaleDData.get(0));
        datas.add(malePData.get(0));
        datas.add(maleDData.get(0));
        List<UserResponseDto> sampleDatas = makeUserData(datas);
        userService.updateApprovedAt(userService.getUserByPhoneNumber(targetUserPhoneNumber).getUserNo(), LocalDateTime.now().minusWeeks(4));
        List<String> expectedResult = Arrays.asList("윤도현", "박서연");

        //when
        List<AutoRecommendedData> result = autoRecommendService.createAutoRecommend(targetUserPhoneNumber);
        List<String> recommendedUserNameList = getUserNameList(result);

        //then
        Assertions.assertEquals(result.size(), 2);
        Assertions.assertTrue(recommendedUserNameList.containsAll(expectedResult));
    }

    @Test
    @Transactional
    public void _2주이상_D등급_여성_사용자_상대방_P2명_매칭결과_P2() {
        //given
        String targetUserPhoneNumber = "1056473821";
        List<String> datas = new ArrayList<>();
        datas.add(femaleDData.get(0));
        datas.add(malePData.get(0));
        datas.add(malePData.get(1));
        List<UserResponseDto> sampleDatas = makeUserData(datas);
        userService.updateApprovedAt(userService.getUserByPhoneNumber(targetUserPhoneNumber).getUserNo(), LocalDateTime.now().minusWeeks(4));
        List<String> expectedResult = Arrays.asList("윤도현", "송지아");

        //when
        List<AutoRecommendedData> result = autoRecommendService.createAutoRecommend(targetUserPhoneNumber);
        List<String> recommendedUserNameList = getUserNameList(result);

        //then
        Assertions.assertEquals(result.size(), 2);
        Assertions.assertTrue(recommendedUserNameList.containsAll(expectedResult));
    }

    @Test
    @Transactional
    public void _2주이상_D등급_여성_사용자_상대방_P1_G1명_매칭결과_P1_G1() {
        //given
        String targetUserPhoneNumber ="1056473821";
        List<String> datas = new ArrayList<>();
        datas.add(femaleDData.get(0));
        datas.add(malePData.get(0));
        datas.add(maleGData.get(0));
        List<UserResponseDto> sampleDatas = makeUserData(datas);
        userService.updateApprovedAt(userService.getUserByPhoneNumber(targetUserPhoneNumber).getUserNo(), LocalDateTime.now().minusWeeks(4));
        List<String> expectedResult = Arrays.asList("윤도현", "김민준");

        //when
        List<AutoRecommendedData> result = autoRecommendService.createAutoRecommend(targetUserPhoneNumber);
        List<String> recommendedUserNameList = getUserNameList(result);

        //then
        Assertions.assertEquals(result.size(), 2);
        Assertions.assertTrue(recommendedUserNameList.containsAll(expectedResult));
    }

    @Test
    @Transactional
    public void _2주이상_D등급_여성_사용자_상대방_D1_G2명_매칭결과_D1_G1() {
        //given
        String targetUserPhoneNumber = "1056473821";
        List<String> datas = new ArrayList<>();
        datas.add(femaleDData.get(0));
        datas.add(maleDData.get(0));
        datas.add(maleGData.get(0));
        datas.add(maleGData.get(1));
        List<UserResponseDto> sampleDatas = makeUserData(datas);
        userService.updateApprovedAt(userService.getUserByPhoneNumber(targetUserPhoneNumber).getUserNo(), LocalDateTime.now().minusWeeks(4));
        List<String> expectedResult = Arrays.asList("박서연", "김지안");

        //when
        List<AutoRecommendedData> result = autoRecommendService.createAutoRecommend(targetUserPhoneNumber);
        List<String> recommendedUserNameList = getUserNameList(result);

        //then
        Assertions.assertEquals(result.size(), 2);
        Assertions.assertTrue(recommendedUserNameList.containsAll(expectedResult));
    }
    private List<String> getUserNameList(List<AutoRecommendedData> result) {
        System.out.println("getUserNameList : " + result.toString() );
        return result.stream()
                .map(AutoRecommendedData::getRecommendedUserName)
                .collect(Collectors.toList());
    }

}