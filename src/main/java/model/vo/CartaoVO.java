package model.vo;

import java.io.Serializable;
import javax.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Data
@Entity
@Table(
	name = "cartao",
	indexes = {
		@Index(columnList = "id", name = "ix_cartao_id"),
		@Index(columnList = "validade", name = "ix_cartao_validade"),
		@Index(columnList = "idCliente", name = "ix_cartao_cliente_id")
	},
	uniqueConstraints = {
		@UniqueConstraint(columnNames = "numero", name = "ix_uq_cartao_numero")
	}
)
public class CartaoVO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@Basic
	@NonNull
	@Column(name = "numero", nullable = false, unique = true)
	private String numero;

	@Basic
	@NonNull
	@Column(name = "bandeira", nullable = false)
	private String bandeira;

	@Basic
	@NonNull
	@Column(name = "validade", nullable = false)
	private String validade;

	@ManyToOne
	@JoinColumn(name = "idCliente")
	private ClienteVO clienteVO;
}
