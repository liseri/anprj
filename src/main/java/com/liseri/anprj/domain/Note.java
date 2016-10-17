package com.liseri.anprj.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

import com.liseri.anprj.domain.enumeration.NoteType;

/**
 * A Note.
 */
@Entity
@Table(name = "an_note")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Note implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "prj_id", nullable = false)
    private Long prjId;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "note_type", nullable = false)
    private NoteType noteType;

    @NotNull
    @Size(max = 1000)
    @Column(name = "note_content", length = 1000, nullable = false)
    private String noteContent;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPrjId() {
        return prjId;
    }

    public Note prjId(Long prjId) {
        this.prjId = prjId;
        return this;
    }

    public void setPrjId(Long prjId) {
        this.prjId = prjId;
    }

    public NoteType getNoteType() {
        return noteType;
    }

    public Note noteType(NoteType noteType) {
        this.noteType = noteType;
        return this;
    }

    public void setNoteType(NoteType noteType) {
        this.noteType = noteType;
    }

    public String getNoteContent() {
        return noteContent;
    }

    public Note noteContent(String noteContent) {
        this.noteContent = noteContent;
        return this;
    }

    public void setNoteContent(String noteContent) {
        this.noteContent = noteContent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Note note = (Note) o;
        if(note.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, note.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Note{" +
            "id=" + id +
            ", prjId='" + prjId + "'" +
            ", noteType='" + noteType + "'" +
            ", noteContent='" + noteContent + "'" +
            '}';
    }
}
