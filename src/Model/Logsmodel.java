/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author liamb
 */
@Entity
@Table(name = "LOGSMODEL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Logsmodel.findAll", query = "SELECT l FROM Logsmodel l")
    , @NamedQuery(name = "Logsmodel.findById", query = "SELECT l FROM Logsmodel l WHERE l.id = :id")
    , @NamedQuery(name = "Logsmodel.findByDate", query = "SELECT l FROM Logsmodel l WHERE l.date = :date")
    , @NamedQuery(name = "Logsmodel.findByLogcontentmodel", query = "SELECT l FROM Logsmodel l WHERE l.logcontentmodel = :logcontentmodel")})
public class Logsmodel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "DATE")
    private String date;
    @Column(name = "LOGCONTENTMODEL")
    private String logcontentmodel;

    public Logsmodel() {
    }

    public Logsmodel(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLogcontentmodel() {
        return logcontentmodel;
    }

    public void setLogcontentmodel(String logcontentmodel) {
        this.logcontentmodel = logcontentmodel;
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
        if (!(object instanceof Logsmodel)) {
            return false;
        }
        Logsmodel other = (Logsmodel) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Logsmodel[ id=" + id + " ]";
    }
    
}
