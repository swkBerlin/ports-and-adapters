package foo.bar.service;

import foo.bar.foo.bar.domain.Observation;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

public class CvsMapperTest {

    //Add test for malformed data
    @Test
    public void testBadData() {
        assertThat(new CvsMapper().map(new Observation(1, "name\"with\"quotes", "1", null)), nullValue());
    }
}