package com.guoanshequ.daqweb.repository;

import com.guoanshequ.daqweb.domain.UploadRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.QueryHint;
import java.util.List;

import static org.hibernate.jpa.QueryHints.HINT_COMMENT;

/**
 * @ProjectName: jgjc
 * @Package: com.guoanshequ.daqweb.repository
 * @Description:
 * @Author: gbl
 * @CreateDate: 2018/11/12 9:42
 */
@Repository
public interface UploadRecordRepository extends JpaRepository<UploadRecord,Long> {

    /**
     * @Description
     * @author gbl
     * @date 2018/11/12 13:52
     **/

    @Query(value = "from UploadRecord ur where ur.type=:type order by ur.id desc ")
    public Page<UploadRecord> findByType(String type,Pageable pageable);

}
