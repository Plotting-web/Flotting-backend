package com.flotting.api.util;

import com.flotting.api.user.SampleDataMaker;
import com.flotting.api.user.model.UserFilterRequestDto;
import com.flotting.api.user.service.UserService;
import com.flotting.api.util.service.ExcelService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class ExcelServiceTest extends SampleDataMaker {

    @Autowired
    private ExcelService excelService;

    @Autowired
    private UserService userService;

    @Test
    @Transactional
    public void 사용자_액셀_다운로드() {
        //given
        makeUserData();
        UserFilterRequestDto userFilterRequestDto = new UserFilterRequestDto();
        MockHttpServletResponse mockHttpServletResponse = new MockHttpServletResponse();

        //when
        userService.downloadExcel(userFilterRequestDto, mockHttpServletResponse);

        //then
    }
}
