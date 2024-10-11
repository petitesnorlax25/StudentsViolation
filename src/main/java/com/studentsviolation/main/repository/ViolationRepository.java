package com.studentsviolation.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.studentsviolation.main.entity.ViolationRecords;
import org.springframework.transaction.annotation.Transactional;


@Transactional
public interface ViolationRepository extends JpaRepository<ViolationRecords, Long> {
    @Query("SELECT v FROM ViolationRecords v JOIN FETCH v.students s WHERE s.programCode = :programCode AND s.year = :year AND s.section = :section")
    List<ViolationRecords> findByCourseAndYearAndSection(
        @Param("programCode") String course, 
        @Param("year") Long year, 
        @Param("section") String section);


    ViolationRecords findById(long id);
    
    List<ViolationRecords> findByStudentsId(long id);
    @Query("SELECT v FROM ViolationRecords v JOIN FETCH v.students s WHERE s.programCode = :programCode")
    List<ViolationRecords> findByCourse(@Param("programCode") String course);
    @Query("SELECT v FROM ViolationRecords v JOIN FETCH v.students s WHERE s.year = :year")
    List<ViolationRecords> findByYear(@Param("year") Long year);
    @Query("SELECT v FROM ViolationRecords v JOIN FETCH v.students s WHERE s.username = :username")
    List<ViolationRecords> findByUsercode(@Param("username") String username);
    @Query("SELECT v FROM ViolationRecords v JOIN FETCH v.students s WHERE s.username = :username AND v.status = :status ")
    List<ViolationRecords> findByUsercodeAndStatus(@Param("username") String username, long status);
    @Query("SELECT v FROM ViolationRecords v JOIN FETCH v.students s WHERE s.programCode = :programCode AND v.status = :status AND v.deptHeadApproval = :deptHeadApproval")
    List<ViolationRecords> findViolationRecordsByProgramCodeAndStatusAndDeptHeadApproval(
        @Param("programCode") String programCode, 
        @Param("status") long status, 
        @Param("deptHeadApproval") long deptHeadApproval);
    @Query("SELECT v FROM ViolationRecords v JOIN FETCH v.students s WHERE s.programCode = :programCode AND v.deptHeadApproval = :deptHeadApproval")
    List<ViolationRecords> findViolationRecordsByProgramCodeAndDeptHeadApproval(
        @Param("programCode") String programCode, 
        @Param("deptHeadApproval") long deptHeadApproval);
    List<ViolationRecords> findViolationRecordsByStatusAndDisciplinaryOfficerApproval(
            @Param("status") long status, 
            @Param("disciplinaryOfficerApproval") long disciplinaryOfficerApproval);
    List<ViolationRecords> findViolationRecordsByDisciplinaryOfficerApproval(
            @Param("disciplinaryOfficerApproval") long disciplinaryOfficerApproval);
    List<ViolationRecords> findViolationRecordsByStatusAndGuidanceOfficerApproval(
            @Param("status") long status, 
            @Param("guidanceOfficerApproval") long guidanceOfficerApproval);
    List<ViolationRecords> findViolationRecordsByGuidanceOfficerApproval(
            @Param("guidanceOfficerApproval") long guidanceOfficerApproval);
    @Query("SELECT v FROM ViolationRecords v JOIN FETCH v.students s WHERE s.programCode = :programCode AND v.status = :status ")
    List<ViolationRecords> findByProgramCodeAndStatus(@Param("programCode") String programCode, long status);
    List<ViolationRecords> findByStatus(int Status);
	@Query("SELECT s.programCode, COUNT(v) FROM ViolationRecords v JOIN v.students s GROUP BY s.programCode")
	 List<Object[]> findTotalViolationsByProgramCode();
	 @Query("SELECT s.year, COUNT(v) FROM ViolationRecords v JOIN v.students s WHERE s.programCode = :programCode GROUP BY s.year")
	 List<Object[]> findTotalViolationsByProgramCodeAndGroupByYearLevel(@Param("programCode") String programCode);
	
 
}
