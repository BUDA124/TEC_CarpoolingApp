package org.tec.carpooling.da.entities;

import jakarta.persistence.*;

public class GenderEntity {
    @Entity
    @Table(name = "GENDER")
    public class Gender {
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_gender_gen")
        @SequenceGenerator(name = "seq_gender_gen", sequenceName = "SEQ_GENDER", allocationSize = 1)
        @Column(name = "ID")
        private Long id;

        @Column(name = "GENDERNAME", nullable = false, length = 50)
        private String genderName;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "IDAUDITLOG", nullable = false)
        private AuditLog auditLog;
}
