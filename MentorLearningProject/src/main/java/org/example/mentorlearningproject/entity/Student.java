package org.example.mentorlearningproject.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name= "student")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_nane")
    private String lastName;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(name="date_of_birth")
    private LocalDate dateOfBirth;

    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id")
    private List<Book> books;

    @ManyToMany
    @JoinTable(
            name = "student_course",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private Set<Course> courses;

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof Student student)) return false;
//        return Objects.equals(getId(), student.getId()) && Objects.equals(getFirstName(), student.getFirstName()) && Objects.equals(getLastName(), student.getLastName()) && Objects.equals(getEmail(), student.getEmail()) && Objects.equals(getDateOfBirth(), student.getDateOfBirth()) && Objects.equals(getAddress(), student.getAddress()) && Objects.equals(getBooks(), student.getBooks()) && Objects.equals(getCourses(), student.getCourses());
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, firstName, lastName, email, dateOfBirth);
//    }

}
