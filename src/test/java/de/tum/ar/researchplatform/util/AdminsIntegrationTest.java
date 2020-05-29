package de.tum.ar.researchplatform.util;

import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

/**
 * Created by karthik on 5/30/2020
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminsIntegrationTest {
    // Base Test
    @Test
    public void testFetchAdmins() {
        List<String> adminList = Lists.newArrayList(Arrays.asList(Admins.admins));
        assertThat(adminList.isEmpty()).isFalse();
    }
}
