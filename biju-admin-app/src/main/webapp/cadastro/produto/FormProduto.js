


	function initialize() {
		jQuery('[id*=preco]').maskMoney({
			thousands:'.', 
			decimal:','
		})
	}	
	
	
	function validateForm() {
		var fields = [
			{
				id: 'nomePeca',
				msg: '\u00C9 obrigat\u00F3rio informar o Nome da Pe\u00E7a',
				type: 'text'
			},
			{
				id: 'preco',
				msg: '\u00C9 obrigat\u00F3rio informar o Pre\u00E7o',
				type: 'text'
			},
			{
				id: 'qtd',
				msg: '\u00C9 obrigat\u00F3rio informar a Quantidade em Estoque',
				type: 'text'
			},
			{
				id: 'categoria_input',
				msg: '\u00C9 obrigat\u00F3rio informar a Categoria',
				type: 'combo'
			}
		]
		
		for (i in fields) {
			var field	= fields[i]
			var element	= jQuery('[id*='+field.id+']')[0]
			
			if (field.type == 'text') {
				if (jQuery.trim(element.value) == '') {
					jQuery('.m-0').text(field.msg)
					PF('msgPanel').show()
					return false
				}
			} else if (element[element.selectedIndex].value == '') {
				jQuery('.m-0').text(field.msg)
				PF('msgPanel').show()
				return false
			}
		}					
		
		return true;		
	}
	
	
	
	
	
	
	
	
	
	