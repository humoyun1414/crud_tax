package com.example.demo_crud_project.repository;

import com.example.demo_crud_project.domain.CalculationTable;
import com.example.demo_crud_project.model.CalculateDto;
import com.example.demo_crud_project.model.CalculateDtoCondition2;
import com.example.demo_crud_project.model.CalculateDtoCondition3;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CalculationTableRepository extends JpaRepository<CalculationTable, Integer> {

    @Query(value = """
             SELECT NEW com.example.demo_crud_project.model.CalculateDto(employee.pinfl,SUM(calculation.rate))FROM CalculationTable as calculation INNER JOIN Employee as employee on calculation.employeeId = employee.id
                        WHERE EXTRACT(MONTH FROM calculation.date) = :monthInt
                        GROUP BY employee.pinfl
                        HAVING SUM(calculation.rate) >= :limitInt
            """)
    List<CalculateDto> calculateRate(@Param(value = "monthInt") int month,
                                     @Param(value = "limitInt") int limit);

    @Query(value = """
            SELECT NEW com.example.demo_crud_project.model.CalculateDtoCondition2(E.pinfl, COUNT(DISTINCT O.id), SUM(CT.rate)) FROM Employee AS E JOIN CalculationTable AS CT ON E.id = CT.employeeId
            JOIN Organization AS O ON E.organizationId = O.id
            WHERE EXTRACT(MONTH FROM CT.date) = :monthInt
            GROUP BY E.pinfl
            HAVING COUNT(DISTINCT O.regionId) > 1
            """)
    List<CalculateDtoCondition2> calculateCondition2(@Param(value = "monthInt") int month);

    @Query(value = """
            SELECT NEW com.example.demo_crud_project.model.CalculateDtoCondition3(E.id,E.firstname,E.hireDate,E.lastname,E.organizationId,E.pinfl, O.name, AVG(CT.rate))
                                                 FROM Employee AS E
                                                 JOIN CalculationTable AS CT ON E.id = CT.employeeId
                                                 JOIN Organization AS O ON E.organizationId = O.id
                                                 WHERE O.id = :orgId
                                                   AND  EXTRACT(MONTH FROM CT.date) = :monthInt
                                                 GROUP BY E.id, O.id
            """)
    List<CalculateDtoCondition3> calculateCondition3(
            @Param(value = "orgId") int orgId,
            @Param(value = "monthInt") int month
    );


}
