package com.example.webhook.service;

import com.example.webhook.util.RegNoUtils;
import org.springframework.stereotype.Service;

@Service
public class SqlSolver {

    public String computeFinalSql(String regNo) {
        boolean isOdd = RegNoUtils.lastTwoDigits(regNo) % 2 == 1;

        String finalQueryOdd = """
            SELECT 
                p.amount AS SALARY,
                CONCAT(e.first_name, ' ', e.last_name) AS NAME,
                TIMESTAMPDIFF(YEAR, e.dob, CURDATE()) AS AGE,
                d.department_name
            FROM payments p
            JOIN employee e ON p.emp_id = e.emp_id
            JOIN department d ON e.department = d.department_id
            WHERE DAY(p.payment_time) <> 1
            ORDER BY p.amount DESC
            LIMIT 1;
            """;

        String finalQueryEven = """
            SELECT 
                e.emp_id,
                e.first_name,
                e.last_name,
                d.department_name,
                COUNT(e2.emp_id) AS younger_employees_count
            FROM employee e
            JOIN department d ON e.department = d.department_id
            LEFT JOIN employee e2 
                ON e.department = e2.department
               AND e2.dob > e.dob
            GROUP BY e.emp_id, e.first_name, e.last_name, d.department_name
            ORDER BY e.emp_id DESC;
            """;

        return isOdd ? finalQueryOdd : finalQueryEven;
    }
}
