package reference.model;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import kz.flabs.dataengine.jpa.AppEntity;
import kz.flabs.localization.LanguageType;

@Entity
@Table(name = "tags", uniqueConstraints = @UniqueConstraint(columnNames = { "parent", "name" }))
@NamedQuery(name = "Tag.findAll", query = "SELECT m FROM Tag AS m WHERE m.parent IS NULL ORDER BY m.name")
public class Tag extends AppEntity {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent")
	private Tag parent;

	@OneToMany(mappedBy = "parent", cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	private List<Tag> children;

	@Column(nullable = false, length = 128)
	private String name;

	@Column(name = "localized_name")
	private Map<LanguageType, String> localizedName;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<LanguageType, String> getLocalizedName() {
		return localizedName;
	}

	public void setLocalizedName(Map<LanguageType, String> name) {
		this.localizedName = name;
	}

	public Tag getParent() {
		return parent;
	}

	public void setParent(Tag parent) {
		this.parent = parent;
	}

	public List<Tag> getChildren() {
		return children;
	}

	public UUID getParentId() {
		if (parent == null) {
			return null;
		}
		return parent.id;
	}

	public void setParentId(UUID id) {
		if (id == null) {
			setParent(null);
			return;
		}

		Tag parent = new Tag();
		parent.setId(id);
		setParent(parent);
	}

	@Override
	public String getShortXMLChunk() {
		return "<name>" + name + "</name>";
	}

	@Override
	public String toString() {
		return "Tag[" + id + ", " + localizedName + ", " + parent + ", " + getAuthor() + ", " + getRegDate() + "]";
	}
}
