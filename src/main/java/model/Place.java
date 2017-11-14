package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Type;
import org.hibernate.validator.NotNull;

/**
 * Created by tuliodesouza on 13/11/17.
 */
@Entity
@Table(name = "PONTO_TURISTICO")
public class Place implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "ID_PONTO_TURISTICO")
  protected Integer id;

  @Basic(optional = true)
  @NotNull
  @Column(name = "NOME")
  private String name;

  @Basic(optional = true)
  @NotNull
  @Column(name = "LATITUDE")
  private String latitude;

  @Basic(optional = true)
  @NotNull
  @Column(name = "LONGITUDE")
  private String longitude;

  @Basic(optional = true)
  @NotNull
  @Column(name = "ALTITUDE")
  private String altitude;

  @Basic(optional = true)
  @NotNull
  @Lob
  @Column(name = "DESCRICAO", length = 65535)
  private String description;

  @Basic(optional = true)
  @NotNull
  @Column(name = "IDIOMA")
  private String language;

  @Basic(optional = true)
  @NotNull
  @Column(name = "LOGRADOURO")
  private String address;

  @JoinColumn(name = "ID_CATEGORIA", referencedColumnName = "ID_CATEGORIA")
  @ManyToOne(optional = false, fetch = FetchType.EAGER)
  private Category category;

  @JoinColumn(name = "ID_MUNICIPIO", referencedColumnName = "ID_MUNICIPIO")
  @ManyToOne(optional = false, fetch = FetchType.EAGER)
  private City city;

  public Place(Integer id) {
    this.id = id;
  }

  public Place() {

  }

  public Place(String name) {
    this.name = name;
  }

  public Integer getId() {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLatitude() {
    return latitude;
  }

  public void setLatitude(String latitude) {
    this.latitude = latitude;
  }

  public String getLongitude() {
    return longitude;
  }

  public void setLongitude(String longitude) {
    this.longitude = longitude;
  }

  public String getAltitude() {
    return altitude;
  }

  public void setAltitude(String altitude) {
    this.altitude = altitude;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  public City getCity() {
    return city;
  }

  public void setCity(City city) {
    this.city = city;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (id != null ? id.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Place)) {
      return false;
    }
    Place other = (Place) object;
    return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
  }

  @Override
  public String toString() {
    return "model.Category[ id=" + id + " ]";
  }
}
