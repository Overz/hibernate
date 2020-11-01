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
	name = "pessoajuridica",
	indexes = {
		@Index(columnList = "id", name = "ix_pessoajuridica_idPessoaJuridica"),
		@Index(columnList = "cnpj", name = "ix_pessoajuridica_cnpj")
	}
)
@PrimaryKeyJoinColumn(name = "id")
public class PessoaJuridicaVO extends ClienteVO {
	private static final long serialVersionUID = 1L;

	@Basic
	@NonNull
	@Column(name = "cnpj", nullable = false, unique = true)
	private String cnpj;

	@Basic
	@NonNull
	@Column(name = "inscricao", nullable = false, unique = true)
	private String inscricao;
}
