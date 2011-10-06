package com.es.zumeh.server.persistence;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;


public class ConsultaParser {
        
        private static final String SEPARADOR_DISJUNCAO = "\\sOR\\s|\\sOU\\s";
        public static final String SEPARADOR_CONJUNCAO = "\\s+AND\\s+|\\s+E\\s+|\\s+";
        private static final String SEPARADOR_CHAVE_VALOR = "\\:|\\=";
        public static final String LONG_REGEX = "[0-9]+";
        public static final String DOUBLE_REGEX = "[0-9]*.[0-9]+";
        public static final String BOOL_REGEX = "true|false";
        
        @SuppressWarnings("unchecked")
        public static Criterion parseConsulta(String consulta) {

                
                Disjunction restricaoDisjuncoes = Restrictions.disjunction();
                String[] conjuncoes = consulta.split(SEPARADOR_DISJUNCAO);
                for (String conjuncao : conjuncoes) {
                        Conjunction restricaoConjuncao = Restrictions.conjunction();
                        String[] tokens = conjuncao.split(SEPARADOR_CONJUNCAO);
                        for (String token : tokens) {
                                Map mapaConsulta = extraiMapaChaveValor(token);
                                restricaoConjuncao.add(Restrictions.allEq(mapaConsulta));
                        }
                        restricaoDisjuncoes.add(restricaoConjuncao);
                }
                return restricaoDisjuncoes;
        }

        @SuppressWarnings("unchecked")
        private static Map extraiMapaChaveValor(String token) {
                Map mapaConsulta = new HashMap();
                String[] chaveValor = token.split(SEPARADOR_CHAVE_VALOR);
                if (chaveValor.length == 2) {
                        String chave = chaveValor[0]; 
                        String valor = chaveValor[1];
                        if( valor.matches(BOOL_REGEX))
                                mapaConsulta.put(chave, Boolean.parseBoolean(valor));
                        else if( valor.matches(LONG_REGEX))
                                mapaConsulta.put(chave, Long.parseLong(valor));
                        else if( valor.matches(DOUBLE_REGEX))
                                mapaConsulta.put(chave, Double.parseDouble(valor));
                        else
                                mapaConsulta.put(chave, valor);
                }
                return mapaConsulta;
        }


}
