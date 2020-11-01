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
	name = "profissao",
	indexes = {
		@Index(columnList = "id", name = "ix_profissao_id"),
		@Index(columnList = "nome", name = "ix_profissao_nome")
	}
)
public class ProfissaoVO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@Basic
	@NonNull
	@Column(name = "nome", nullable = false)
	private String nome;

	@Basic
	@NonNull
	@Column(name = "descricao")
	private String descricao;
}
