CREATE VIEW TOTAL_VENDAS_POR_DATA AS
	SELECT 	v.DATAVENDA			 AS data_venda,
			sum(i.QTD * i.PRECO) AS valor_total 
	FROM	venda v,
			ITEMVENDA i
	WHERE  	v.ID = i.ID_VENDA
	GROUP BY v.data_venda 

	