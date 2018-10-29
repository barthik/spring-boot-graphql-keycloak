package graphql.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
    @Id
    @Column(name = "user_id", nullable = false)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(name = "user_username", nullable = false)
    private String username;

    @Column(name = "user_email", nullable = false)
    private String email;

    @Column(name = "user_request_count", nullable = false)
    private int requestCount;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false, updatable = false)
    private Role role;

    @Enumerated(EnumType.STRING)
    @JoinColumn(name = "user_state", nullable = false)
    private State state;

    public enum State {
        ACTIVE,
        DISABLED,
        BLOCKED
    }
}
