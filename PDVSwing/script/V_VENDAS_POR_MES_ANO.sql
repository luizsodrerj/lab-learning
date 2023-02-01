CREATE VIEW TOTAL_VENDAS_MES_ANO AS
	SELECT	concat(
			  cast(year(v.data_venda) AS varchar(4)),
			  CASE WHEN CHARACTER_LENGTH(cast(month(v.data_venda) AS varchar(2))) < 2 
			  THEN concat('0',cast(month(v.data_venda) AS varchar(2))) 
			  ELSE cast(month(v.data_venda) AS varchar(2)) END 
			) AS ano_mes,
			sum(v.VALOR_TOTAL) as valor_total 
	FROM 	TOTAL_VENDAS_POR_DATA v
	GROUP BY ano_mes
 