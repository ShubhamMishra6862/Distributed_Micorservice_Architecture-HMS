package com.pm.patientservice.search;

import java.time.LocalDate;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

@Data
@Document(indexName = "patients")
@Setting(settingPath = "elasticsearch/patients-settings.json")
@Mapping(mappingPath = "elasticsearch/patients-mapping.json")
public class PatientDocument {

  @Id
  private String id;

  @MultiField(
          mainField = @Field(type = FieldType.Text),
          otherFields = {
                  @InnerField(suffix = "keyword", type = FieldType.Keyword)
          }
  )
  private String name;

  @Field(type = FieldType.Keyword)
  private String email;

  @Field(type = FieldType.Text)
  private String address;

  @Field(type = FieldType.Date, format = DateFormat.date)
  private LocalDate dateOfBirth;

  @Field(type = FieldType.Date, format = DateFormat.date)
  private LocalDate registeredDate;

  @Field(type = FieldType.Double)
  private Double height;

  @Field(type = FieldType.Double)
  private Double weight;
}