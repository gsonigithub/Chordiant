package com.vodacom.chordiant.reporting.data.repository;

import java.sql.Blob;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.vodacom.chordiant.reporting.data.mapping.ChordiantAvailabilityEntity;

@SequenceGenerator(name = "ATTACHMENT_ID_GEN", sequenceName = "ATTACHMENT_ID_GEN")
@Entity
@Table(name = "ATTACHMENT")
public class AttachmentEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ATTACHMENT_ID_GEN")
	private Long id;

	@Column(name = "TITLE")
	private String title;

	private Blob content;

	@Column(name = "FILE_TYPE")
	private String fileType;

	@Column(name = "FILE_SIZE_IN_BYTES")
	private Long sizeInBytes;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	ChordiantAvailabilityEntity chordiantAvailability;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the content
	 */
	public Blob getContent() {
		return content;
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void setContent(Blob content) {
		this.content = content;
	}

	/**
	 * @return the fileType
	 */
	public String getFileType() {
		return fileType;
	}

	/**
	 * @param fileType
	 *            the fileType to set
	 */
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	/**
	 * @return the sizeInBytes
	 */
	public Long getSizeInBytes() {
		return sizeInBytes;
	}

	/**
	 * @param sizeInBytes
	 *            the sizeInBytes to set
	 */
	public void setSizeInBytes(Long sizeInBytes) {
		this.sizeInBytes = sizeInBytes;
	}

	/**
	 * @return the chordiantAvailability
	 */
	public ChordiantAvailabilityEntity getChordiantAvailability() {
		return chordiantAvailability;
	}

	/**
	 * @param chordiantAvailability
	 *            the chordiantAvailability to set
	 */
	public void setChordiantAvailability(
			ChordiantAvailabilityEntity chordiantAvailability) {
		this.chordiantAvailability = chordiantAvailability;
	}

}
