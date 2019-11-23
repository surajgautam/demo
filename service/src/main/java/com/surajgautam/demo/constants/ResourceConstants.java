package com.surajgautam.demo.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Created by Suraj Gautam.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ResourceConstants {

    public static final String BASE_URL = "/api/v1";

    public interface EmployeeResource{
        String URL = "/employees";
    }

}
