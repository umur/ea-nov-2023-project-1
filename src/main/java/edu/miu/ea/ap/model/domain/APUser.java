package edu.miu.ea.ap.model.domain;

import edu.miu.ea.ap.helper.Enumerations;
import edu.miu.ea.ap.helper.converter.ListOfStringConverter;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "AP_USERS")
@Data
public class APUser extends APBaseEntityWithIdAuto implements UserDetails {

    @Transient
    Collection<SimpleGrantedAuthority> authorities;

    @Column(unique = true)
    private String username;
    private String mobileNumber;
    @Column(unique = true)
    private String email;
    private String language;
    private Date lastSignIn;

    private String nameFirst;
    private String nameLast;
    private APAddress location;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfBirth;

    @Enumerated(EnumType.STRING)
    private Enumerations.RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date graduationDate;

    @Convert(converter = ListOfStringConverter.class)
    private List<String> contactInfo;

    @Convert(converter = ListOfStringConverter.class)
    private List<String> educationalDetails;

    @Convert(converter = ListOfStringConverter.class)
    private List<String> professionalAchievements;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PICTURE_ID")
    private APFile picture;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "AP_USERS_PRIVILEGES",
            joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "PRIVILEGE_ID")
    )
    private Set<APPrivilege> privileges;

    @ManyToMany(mappedBy = "students")
    private Set<APCourse> courses;

    @OneToMany(mappedBy = "advertiser")
    private Set<APJob> jobsAdvertised;

    @ManyToMany(mappedBy = "applicants")
    private Set<APJob> jobsApplied;

    @OneToMany(mappedBy = "publisher")
    private Set<APNewsItem> newsPublished;

    @OneToMany(mappedBy = "organizer")
    private Set<APEvent> eventsOrganized;

    @ManyToMany(mappedBy = "attendees")
    private Set<APEvent> eventsAttended;

    @ManyToMany(mappedBy = "attendees")
    private Set<APMeeting> meetingsAttended;

    @OneToMany(mappedBy = "organizer")
    @OrderBy(value = "createdAt DESC")
    private Set<APMeeting> meetingsOrganized;

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
