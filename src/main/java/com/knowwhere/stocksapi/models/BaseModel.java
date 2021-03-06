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

      @JsonIgnore
      @Basic
      @Column(name = "createdBy")
      private Long createdBy;


      @JsonIgnore
      @Temporal(TemporalType.TIMESTAMP)
      @LastModifiedDate
      @Column(name = "updatedAt")
      private Date updatedAt;


      public Long getCreatedBy() {
            return createdBy;
      }

      public Date getUpdatedAt() {
            return updatedAt;
      }

      public void setCreatedBy(Long createdBy) {
            this.createdBy = createdBy;
      }


      public void setUpdatedAt(Date updatedAt) {
            this.updatedAt = updatedAt;
      }

      @Override
      public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            BaseModel baseModel = (BaseModel) o;
            return Objects.equals(createdBy, baseModel.createdBy) &&
                    Objects.equals(updatedAt, baseModel.updatedAt);
      }

      @Override
      public int hashCode() {
            return Objects.hash(createdBy, updatedAt);
      }

      @Override
      public String toString() {
            return "BaseModel{" +
                    ", createdBy=" + createdBy +
                    ", updatedAt=" + updatedAt +
                    '}';
      }
}
