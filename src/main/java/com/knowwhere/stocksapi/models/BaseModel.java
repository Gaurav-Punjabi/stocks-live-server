package com.knowwhere.stocksapi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.Objects;

public class BaseModel {

      @Temporal(TemporalType.TIMESTAMP)
      @CreationTimestamp
      @Column(name = "createdAt", columnDefinition = "TIMESTAMP DEFAULT CURRENT TIMESTAMP")
      private Date createdAt;

      @JsonIgnore
      @Basic
      @Column(name = "createdBy")
      private Long createdBy;


      @JsonIgnore
      @Column(columnDefinition = "TINYINT")
      @Type(type = "org.hibernate.type.NumericBooleanType")
      private boolean deleted = false;

      @JsonIgnore
      @Temporal(TemporalType.TIMESTAMP)
      @LastModifiedDate
      @Column(name = "updatedAt")
      private Date updatedAt;

      public Date getCreatedAt() {
            return createdAt;
      }

      public Long getCreatedBy() {
            return createdBy;
      }

      public boolean isDeleted() {
            return deleted;
      }

      public Date getUpdatedAt() {
            return updatedAt;
      }

      public void setCreatedAt(Date createdAt) {
            this.createdAt = createdAt;
      }

      public void setCreatedBy(Long createdBy) {
            this.createdBy = createdBy;
      }

      public void setDeleted(boolean deleted) {
            this.deleted = deleted;
      }

      public void setUpdatedAt(Date updatedAt) {
            this.updatedAt = updatedAt;
      }

      @Override
      public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            BaseModel baseModel = (BaseModel) o;
            return deleted == baseModel.deleted &&
                    Objects.equals(createdAt, baseModel.createdAt) &&
                    Objects.equals(createdBy, baseModel.createdBy) &&
                    Objects.equals(updatedAt, baseModel.updatedAt);
      }

      @Override
      public int hashCode() {
            return Objects.hash(createdAt, createdBy, deleted, updatedAt);
      }

      @Override
      public String toString() {
            return "BaseModel{" +
                    "createdAt=" + createdAt +
                    ", createdBy=" + createdBy +
                    ", deleted=" + deleted +
                    ", updatedAt=" + updatedAt +
                    '}';
      }
}
