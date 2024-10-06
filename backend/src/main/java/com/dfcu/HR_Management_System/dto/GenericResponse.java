/**
 * Created By Eng. Pius Obonyo
 * Date: 10/6/24
 * Time: 11:40 AM
 */

package com.dfcu.HR_Management_System.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GenericResponse<T> {

    private String responseCode;
    private T data;

}
