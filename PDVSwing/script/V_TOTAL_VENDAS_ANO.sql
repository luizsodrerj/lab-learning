CREATE VIEW TOTAL_VENDAS_ANO AS
	SELECT	year(v.data_venda) AS ano,  
			SUM(v.valor_total) AS valor_total  
	FROM 	total_vendas_por_data v  
	GROUP BY year(v.data_venda)

	