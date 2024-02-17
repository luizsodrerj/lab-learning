

	function init(statusFilterPanel, statusResultPanel) {
		if (statFiltPnl != '') {
			jQuery('#filterPanel').css('visibility', statusFilterPanel)	
		}
		if (statFiltPnl == 'hidden') {
			jQuery('#filterPanel').hide();	
		}
			
		if (statResPnl != '') {
			jQuery('#resultPanel').css('visibility', statusResultPanel)
			
			if (statResPnl == 'visible') {
				jQuery('#checkResult')[0].checked = true
			}	
		}
	}
	
	function onClickBtShowFiltPanel() {
		jQuery('#checkResult')[0].checked = false
		jQuery('#filterPanel').css('visibility', 'visible')
		jQuery('#filterPanel').show();
		jQuery('#resultPanel').hide();
	}		

	function onClickBtShowResultPanel() {
		jQuery('#checkFilt')[0].checked = false
		jQuery('#resultPanel').css('visibility', 'visible')
		jQuery('#resultPanel').show()
		jQuery('#filterPanel').hide()
	}		
	