package model.vo;

import javax.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@SuperBuilder(toBuilder = true, setterPrefix = "set")
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(
	name = "pessoafisica",
	indexes = {
		@Index(columnList = "id", name = "ix_pessoafisica_id"),
		@Index(columnList = "cpf", name = "ix_pessoaficisa_cpf")
	}
)
@PrimaryKeyJoinColumn(name = "id")
public class PessoaFisicaVO extends ClienteVO {
	private static final long serialVersionUID = 1L;

	@Basic
	@NonNull
	@Column(name = "cpf", nullable = false, unique = true)
	private String cpf;

	@Basic
	@NonNull
	@Column(name = "rg", nullable = false, unique = true)
	private String rg;

	@ManyToOne(cascade = { CascadeType.ALL }, targetEntity = ProfissaoVO.class)
	@JoinColumn(name = "idProfissao")
	private ProfissaoVO profissaoVO;
}
