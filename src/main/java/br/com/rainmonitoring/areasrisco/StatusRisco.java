package br.com.rainmonitoring.areasrisco;

public enum StatusRisco {
    CRITICO("risco altissimo"){
        @Override
        public String avaliarRisco(Integer indicePluvial) {
            String risco = indicePluvial < 10 ? "baixo" :
                           indicePluvial > 10 && indicePluvial < 30 ? "medio": "alto";
            return risco;
        }
    },
    ALTO("risco alto"){
        @Override
        public String avaliarRisco(Integer indicePluvial) {
            String risco = indicePluvial < 20 ? "baixo" :
                    indicePluvial > 20 && indicePluvial < 40 ? "medio": "alto";
            return null;
        }
    },
    MEDIO("risco medio"){
        @Override
        public String avaliarRisco(Integer indicePluvial) {
            String risco = indicePluvial < 30 ? "baixo" :
                    indicePluvial > 30 && indicePluvial < 50 ? "medio": "alto";
            return null;
        }
    },
    BAIXO("risco Baixo"){
        @Override
        public String avaliarRisco(Integer indicePluvial) {
            String risco = indicePluvial < 40 ? "baixo" :
                    indicePluvial > 40 && indicePluvial < 50 ? "medio": "alto";
            return null;
        }
    };

    private String descricao;

    StatusRisco(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public abstract String avaliarRisco(Integer indicePluvial);
}
