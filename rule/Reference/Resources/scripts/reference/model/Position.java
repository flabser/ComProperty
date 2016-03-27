package reference.model;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * 
 * 
 * @author Kayra created 07-01-2016
 */

@Entity
@Table(name = "positions", uniqueConstraints = @UniqueConstraint(columnNames = { "name" }))
@NamedQuery(name = "Position.findAll", query = "SELECT m FROM Position AS m ORDER BY m.regDate")
public class Position extends Reference {

}
