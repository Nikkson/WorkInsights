package com.corporatemasters.workinsights.util;

import com.corporatemasters.workinsights.dto.EmployeeProjectDTO;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CSVReader {
    private static String[] DATE_FORMATS = new String[]{"yyyy/MM/dd", "dd/MM/yyyy", "yyyy-MM-dd"};
    private static EmployeeProjectDTO getEmployeeProjectFromLine (String line) throws IllegalArgumentException {
        String[] lineArr = line.split(",");
        if (lineArr.length != 4) throw new IllegalArgumentException("Invalid record format!");
        try {
            long employeeId = Integer.parseInt(lineArr[0].strip());
            long projectId = Integer.parseInt(lineArr[1].strip());
            Date startDate = DateUtils.parseDate(lineArr[2].strip(),DATE_FORMATS);
            Date endDate;
            if (lineArr[3].strip().equalsIgnoreCase("null")) {
                endDate = null;
            } else {
                endDate = DateUtils.parseDate(lineArr[3], DATE_FORMATS);
            }
            if (endDate!=null && endDate.before(startDate)) {
                throw new IllegalArgumentException("One or more end dates are before the start dates!");
            }
            return new EmployeeProjectDTO(employeeId, projectId, startDate, endDate);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid ID format!");
        } catch (ParseException e) {
            throw new IllegalArgumentException("Unsupported date format!");
        }
    }
    public static List<EmployeeProjectDTO> getAllEmployeeProjectRecords(MultipartFile file) throws IllegalArgumentException, IOException {
        List<EmployeeProjectDTO> employeeProjectDTOs = new ArrayList<>();
        InputStream inputStream = file.getInputStream();
        new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))
                .lines()
                .forEach( line -> employeeProjectDTOs.add(getEmployeeProjectFromLine(line)));
        return employeeProjectDTOs;
    }
}
