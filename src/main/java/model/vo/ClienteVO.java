package model.vo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder(toBuilder = true, setterPrefix = "set")
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(
	name = "cliente",
	indexes = {
		@Index(columnList = "id", name = "ix_cliente_id"),
		@Index(columnList = "nome", name = "ix_cliente_nome")
	}
)
@Inheritance(strategy = InheritanceType.JOINED)
public class ClienteVO implements Serializable {
	@Id
	@GeneratedValue
	private Long id;

	@Basic
	@Column(name = "nome", nullable = false)
	private String nome;

	@Basic
	@Column(name = "email")
	private String email;

	@OneToMany(
		mappedBy = "clienteVO",
		fetch = FetchType.EAGER,
		targetEntity = CartaoVO.class,
		cascade = { CascadeType.ALL }
	)
	private List<CartaoVO> cartaoVO;
}
