$(function() {
	try {
		$(window).load(function() {
		   	$('#loading').hide();
		});
	 	
		$('ul.slimmenu').slimmenu({
		    resizeWidth: '800',
		    collapserTitle: 'Menus',
		    easingEffect:'easeInOutQuint',
		    animSpeed:'medium',
		    indentChildren: true,
		    childrenIndenter: '&nbsp;&nbsp;&nbsp;&raquo;&nbsp;&nbsp;&nbsp;'
		});
		var dt = new Date();
		dt.setFullYear(new Date().getFullYear()-18);
		$('#idob').datepicker({
			todayHighlight: true,
        	format: "dd/mm/yyyy",
		  	viewMode: "years",
		  	endDate: dt
		}).on('changeDate', function(e){
            $(this).datepicker('hide');
        });
		$('#rpolicyEndDateSearch').datepicker({
			todayHighlight: true,
			format: "dd/mm/yyyy"
		}).on('changeDate', function(e){
		    $(this).datepicker('hide');
		});
		$('#rpolicyStartDateSearch').datepicker({
			todayHighlight: true,
			format: "dd/mm/yyyy"
		}).on('changeDate', function(e){
		    $(this).datepicker('hide');
		});
		$('#policyStartDate').datepicker({
			todayHighlight: true,
			format: "dd/mm/yyyy",
			startDate: '-0d'
		}).on('changeDate', function(e){
		    $(this).datepicker('hide');
		});
		
		$('#effecdate').datepicker({
			todayHighlight: true,
			format: "dd/mm/yyyy",
			startDate: '-0d'
		}).on('changeDate', function(e){
		    $(this).datepicker('hide');
		});
		
		$('#invoiceDate').datepicker({
			todayHighlight: true,
			format: "dd/mm/yyyy",
			startDate: '-0d'
		}).on('changeDate', function(e){
		    $(this).datepicker('hide');
		});
		
		$('#manufactureDate').datepicker({
			todayHighlight: true,
			format: "dd/mm/yyyy"
		}).on('changeDate', function(e){
		    $(this).datepicker('hide');
		});
		
		$('#trailer1MfrDate').datepicker({
			todayHighlight: true,
			format: "dd/mm/yyyy"
		}).on('changeDate', function(e){
		    $(this).datepicker('hide');
		});
		
		$('#trailer2MfrDate').datepicker({
			todayHighlight: true,
			format: "dd/mm/yyyy"
		}).on('changeDate', function(e){
		    $(this).datepicker('hide');
		});
		
		$('#inceptionDt').datepicker({
			todayHighlight: true,
			format: "dd/mm/yyyy",
			startDate: '-0d',
			endDate: '+3650d'
		}).on('changeDate', function(e){
		    $(this).datepicker('hide');
		});
		
		$('#expiryDt').datepicker({
			todayHighlight: true,
			format: "dd/mm/yyyy",
			startDate: '-0d',
			endDate: '+3650d'
		}).on('changeDate', function(e){
		    $(this).datepicker('hide');
		});
		$('#paDob').datepicker({
			todayHighlight: true,
        	format: "dd/mm/yyyy",
		  	viewMode: "years",
		  	endDate: dt
		}).on('changeDate', function(e){
            $(this).datepicker('hide');
        });
		$('#dob').datepicker({
			todayHighlight: true,
        	format: "dd/mm/yyyy",
		  	viewMode: "years",
		  	endDate: dt
		}).on('changeDate', function(e){
            $(this).datepicker('hide');
        });		
		$('#blDate').datepicker({
			todayHighlight: true,
			format: "dd/mm/yyyy",
			startDate: "-60d"
			//endDate: '+0d'
		}).on('changeDate', function(e){
		    $(this).datepicker('hide');
		});
		$('#lcDate').datepicker({
			todayHighlight: true,
			format: "dd/mm/yyyy"
		}).on('changeDate', function(e){
		    $(this).datepicker('hide');
		});
		$('#sailingDate').datepicker({
			todayHighlight: true,
			format: "dd/mm/yyyy"
		}).on('changeDate', function(e){
		    $(this).datepicker('hide');
		});
		
		$('#chequeDate').datepicker({
			todayHighlight: true,
			format: "dd/mm/yyyy",
			startDate: "-60d",
			endDate: '+0d'
		}).on('changeDate', function(e){
		    $(this).datepicker('hide');
		});
		
		$('#cashInstrumentDate').datepicker({
			todayHighlight: true,
			format: "dd/mm/yyyy"
		}).on('changeDate', function(e){
		    $(this).datepicker('hide');
		});
		
		$('#ddDate').datepicker({
			todayHighlight: true,
			format: "dd/mm/yyyy"
		}).on('changeDate', function(e){
		    $(this).datepicker('hide');
		});
		
		$('#startDate').datepicker({
			todayHighlight: true,
			format: "dd/mm/yyyy",
			endDate: '+0d'
		}).on('changeDate', function(e){
		    $(this).datepicker('hide');
		});
		
		$('#endDate').datepicker({
			todayHighlight: true,
			format: "dd/mm/yyyy",
			endDate: '+0d'
		}).on('changeDate', function(e){
		    $(this).datepicker('hide');
		});
		$('#depositDate').datepicker({
			todayHighlight: true,
			format: "dd/mm/yyyy",
			endDate: '+0d'
		}).on('changeDate', function(e){
		    $(this).datepicker('hide');
		});
		$('#rpolicyStartDate').datepicker({
			todayHighlight: true,
			format: "dd/mm/yyyy",
			startDate: '-0d'
		}).on('changeDate', function(e){
		    $(this).datepicker('hide');
		});
		$('#rpolicyEndDate').datepicker({
			todayHighlight: true,
			format: "dd/mm/yyyy"
		}).on('changeDate', function(e){
		    $(this).datepicker('hide');
		});
		$('#paPrevExpiryDate').datepicker({
			todayHighlight: true,
			format: "dd/mm/yyyy"
		}).on('changeDate', function(e){
		    $(this).datepicker('hide');
		});
		$('#policyStartDate1').datepicker({
			todayHighlight: true,
			format: "dd/mm/yyyy",
			startDate: '-0d'
		}).on('changeDate', function(e){
		    $(this).datepicker('hide');
		});
		$('#fromdate').datepicker({
			todayHighlight: true,
			format: "dd/mm/yyyy",
			endDate: '-0d'
		}).on('changeDate', function(e){
		    $(this).datepicker('hide');
		});
		$('#todate').datepicker({
			todayHighlight: true,
			format: "dd/mm/yyyy",
			endDate: '-0d'
		}).on('changeDate', function(e){
		    $(this).datepicker('hide');
		});
		
		$('#horizontalTab').easyResponsiveTabs({
			type: 'default', //Types: default, vertical, accordion           
			width: 'auto', //auto or any width like 600px
			fit: true,   // 100% fit in a container
			closed: 'accordion'
		});
		$('#ddlCars2').multiselect({ 
	         includeSelectAllOption: true,
	           enableFiltering:true         
	           
	     });
		$('#effectiveDate').datepicker({
			todayHighlight: true,
			format: "dd/mm/yyyy"
		}).on('changeDate', function(e){
		    $(this).datepicker('hide');
		});
		$('#expiryDate').datepicker({
			todayHighlight: true,
			format: "dd/mm/yyyy"
		}).on('changeDate', function(e){
		    $(this).datepicker('hide');
		});
		$('#disDate').datepicker({
			todayHighlight: true,
			format: "dd/mm/yyyy"
		}).on('changeDate', function(e){
		    $(this).datepicker('hide');
		});
		$('#drateDate').datepicker({
			todayHighlight: true,
			format: "dd/mm/yyyy"
		}).on('changeDate', function(e){
		    $(this).datepicker('hide');
		});
		$('#coverageDate').datepicker({
			todayHighlight: true,
			format: "dd/mm/yyyy"
		}).on('changeDate', function(e){
		    $(this).datepicker('hide');
		});
		$('#optionDate').datepicker({
			todayHighlight: true,
			format: "dd/mm/yyyy"
		}).on('changeDate', function(e){
		    $(this).datepicker('hide');
		});
		$('#schemeDate').datepicker({
			todayHighlight: true,
			format: "dd/mm/yyyy"
		}).on('changeDate', function(e){
		    $(this).datepicker('hide');
		});
		
		/*$('.numericOnly').keypress(function (e) {
			 if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
	               return false;
		    }
		});
		$('.decimalOnly').keypress(function(event) {
		    if ((event.which != 46 || $(this).val().indexOf('.') != -1) && (event.which < 48 || event.which > 57)) {
		      event.preventDefault();
		    } else {
		         var entered_value = $(this).val();
		        var regexPattern = /^\d{0,8}(\.\d{1,2})?$/; 
		        
		    }
		});*/
		
		
		//Motor Start
		function getPolicyEndDate(policyStartDt) {
			if(policyStartDt!="") {
				var date=new Date(reformatDate(policyStartDt));
				//date.setFullYear(date.getFullYear()+1);
				date.setMonth(date.getMonth()+13);
				date.setDate(date.getDate()-1);
				var d;
				var m
				if(parseInt(date.getDate())<10)
				{
					d="0"+date.getDate();
				}else
				{
					d=date.getDate();
				}
				if(parseInt(date.getMonth())==0)
				{
					m="12";
				}else
				if((parseInt(date.getMonth()))<10)
				{
					m="0"+(parseInt(date.getMonth()));
				}else
				{
					m=(parseInt(date.getMonth()));
				}
				var y=date.getFullYear();
				return d+"/"+m+"/"+y;
			} else {
				return "";
			}
		}
		function reformatDate(dateStr) { 
			dArr = dateStr.split("/"); // ex input "2010-01-18" 
			return dArr[2]+ "/" +dArr[1]+ "/" +dArr[0]; //ex out: "18/01/2010"
		}
		$('#motorPolicyStartDate').datepicker({
			todayHighlight: true,
			format: "dd/mm/yyyy",
			startDate: '-0d'
		}).on('changeDate', function(e){
		    $(this).datepicker('hide');
		    //$('#motorPolicyEndDate').val(getPolicyEndDate($('#motorPolicyStartDate').val()));
		});
		
		$('#motorPolicyEndDate').datepicker({
			todayHighlight: true,
			format: "dd/mm/yyyy",
			startDate: '-0d'
		}).on('changeDate', function(e){
		    $(this).datepicker('hide');
		});
		$('#driverDOB').datepicker({
			todayHighlight: true,
        	format: "dd/mm/yyyy",
		  	viewMode: "years",
		  	endDate: dt
		}).on('changeDate', function(e){
            $(this).datepicker('hide');
        });
		$('#custdob').datepicker({
			todayHighlight: true,
        	format: "dd/mm/yyyy",
		  	viewMode: "years",
		  	endDate: dt
		}).on('changeDate', function(e){
            $(this).datepicker('hide');
        });
		$('#startDate1').datepicker({
			todayHighlight: true,
			format: "dd/mm/yyyy",
		}).on('changeDate', function(e){
		    $(this).datepicker('hide');
		});
		
		$('#endDate1').datepicker({
			todayHighlight: true,
			format: "dd/mm/yyyy",
		}).on('changeDate', function(e){
		    $(this).datepicker('hide');
		});
		$('#intiExpiryDate').datepicker({
			todayHighlight: true,
			format: "dd/mm/yyyy"
		}).on('changeDate', function(e){
		    $(this).datepicker('hide');
		});
		//Motor End
		
/***************Image Gallery********************/
		
		// ACTIVITY INDICATOR

		var activityIndicatorOn = function()
			{
				$( '<div class="zoomed" id="imagelightbox-loading"><div></div></div>' ).appendTo( 'body' );
			},
			activityIndicatorOff = function()
			{
				$( '#imagelightbox-loading' ).remove();
			},


			// OVERLAY

			overlayOn = function()
			{
				$( '<div id="imagelightbox-overlay"></div>' ).appendTo( 'body' );
			},
			overlayOff = function()
			{
				$( '#imagelightbox-overlay' ).remove();
			},


			// CLOSE BUTTON

			closeButtonOn = function( instance )
			{
				$( '<button type="button" id="imagelightbox-close" title="Close"></button>' ).appendTo( 'body' ).on( 'click touchend', function(){ $( this ).remove(); instance.quitImageLightbox(); return false; });
			},
			closeButtonOff = function()
			{
				$( '#imagelightbox-close' ).remove();
			},


			// CAPTION

			captionOn = function()
			{
				var description = $( 'a[href="' + $( '#imagelightbox' ).attr( 'src' ) + '"] img' ).attr( 'alt' );
				if( description.length > 0 )
					$( '<div id="imagelightbox-caption">' + description + '</div>' ).appendTo( 'body' );
			},
			captionOff = function()
			{
				$( '#imagelightbox-caption' ).remove();
			},


			// NAVIGATION

			navigationOn = function( instance, selector )
			{
				var images = $( selector );
				if( images.length )
				{
					var nav = $( '<div id="imagelightbox-nav"></div>' );
					for( var i = 0; i < images.length; i++ )
						nav.append( '<button type="button"></button>' );

					nav.appendTo( 'body' );
					nav.on( 'click touchend', function(){ return false; });

					var navItems = nav.find( 'button' );
					navItems.on( 'click touchend', function()
					{
						var $this = $( this );
						if( images.eq( $this.index() ).attr( 'href' ) != $( '#imagelightbox' ).attr( 'src' ) )
							instance.switchImageLightbox( $this.index() );

						navItems.removeClass( 'active' );
						navItems.eq( $this.index() ).addClass( 'active' );

						return false;
					})
					.on( 'touchend', function(){ return false; });
				}
			},
			navigationUpdate = function( selector )
			{
				var items = $( '#imagelightbox-nav button' );
				items.removeClass( 'active' );
				items.eq( $( selector ).filter( '[href="' + $( '#imagelightbox' ).attr( 'src' ) + '"]' ).index( selector ) ).addClass( 'active' );
			},
			navigationOff = function()
			{
				$( '#imagelightbox-nav' ).remove();
			},


			// ARROWS

			arrowsOn = function( instance, selector )
			{
				var $arrows = $( '<button type="button" class="imagelightbox-arrow imagelightbox-arrow-left"></button><button type="button" class="imagelightbox-arrow imagelightbox-arrow-right"></button>' );

				$arrows.appendTo( 'body' );

				$arrows.on( 'click touchend', function( e )
				{
					e.preventDefault();

					var $this	= $( this ),
						$target	= $( selector + '[href="' + $( '#imagelightbox' ).attr( 'src' ) + '"]' ),
						index	= $target.index( selector );

					if( $this.hasClass( 'imagelightbox-arrow-left' ) )
					{
						index = index - 1;
						if( !$( selector ).eq( index ).length )
							index = $( selector ).length;
					}
					else
					{
						index = index + 1;
						if( !$( selector ).eq( index ).length )
							index = 0;
					}

					instance.switchImageLightbox( index );
					return false;
				});
			},
			arrowsOff = function()
			{
				$( '.imagelightbox-arrow' ).remove();
			};


		//	WITH ACTIVITY INDICATION

		$( 'a[data-imagelightbox="a"]' ).imageLightbox(
		{
			onLoadStart:	function() { activityIndicatorOn(); },
			onLoadEnd:		function() { activityIndicatorOff(); },
			onEnd:	 		function() { activityIndicatorOff(); }
		});


		//	WITH OVERLAY & ACTIVITY INDICATION

		$( 'a[data-imagelightbox="b"]' ).imageLightbox(
		{
			onStart: 	 function() { overlayOn(); },
			onEnd:	 	 function() { overlayOff(); activityIndicatorOff(); },
			onLoadStart: function() { activityIndicatorOn(); },
			onLoadEnd:	 function() { activityIndicatorOff(); }
		});


		//	WITH "CLOSE" BUTTON & ACTIVITY INDICATION

		var instanceC = $( 'a[data-imagelightbox="c"]' ).imageLightbox(
		{
			quitOnDocClick:	false,
			onStart:		function() { closeButtonOn( instanceC ); },
			onEnd:			function() { closeButtonOff(); activityIndicatorOff(); },
			onLoadStart: 	function() { activityIndicatorOn(); },
			onLoadEnd:	 	function() { activityIndicatorOff(); }
		});


		//	WITH CAPTION & ACTIVITY INDICATION

		$( 'a[data-imagelightbox="d"]' ).imageLightbox(
		{
			onLoadStart: function() { captionOff(); activityIndicatorOn(); },
			onLoadEnd:	 function() { captionOn(); activityIndicatorOff(); },
			onEnd:		 function() { captionOff(); activityIndicatorOff(); }
		});


		//	WITH ARROWS & ACTIVITY INDICATION

		var selectorG = 'a[data-imagelightbox="g"]';
		var instanceG = $( selectorG ).imageLightbox(
		{
			onStart:		function(){ arrowsOn( instanceG, selectorG ); },
			onEnd:			function(){ arrowsOff(); activityIndicatorOff(); },
			onLoadStart: 	function(){ activityIndicatorOn(); },
			onLoadEnd:	 	function(){ $( '.imagelightbox-arrow' ).css( 'display', 'block' ); activityIndicatorOff(); }
		});


		//	WITH NAVIGATION & ACTIVITY INDICATION

		var selectorE = 'a[data-imagelightbox="e"]';
		var instanceE = $( selectorE ).imageLightbox(
		{
			onStart:	 function() { navigationOn( instanceE, selectorE ); },
			onEnd:		 function() { navigationOff(); activityIndicatorOff(); },
			onLoadStart: function() { activityIndicatorOn(); },
			onLoadEnd:	 function() { navigationUpdate( selectorE ); activityIndicatorOff(); }
		});


		//	ALL COMBINED

		var selectorF = 'a[data-imagelightbox="f"]';
		var instanceF = $( selectorF ).imageLightbox(
		{
			onStart:		function() { overlayOn(); closeButtonOn( instanceF ); arrowsOn( instanceF, selectorF ); },
			onEnd:			function() { overlayOff(); captionOff(); closeButtonOff(); arrowsOff(); activityIndicatorOff(); },
			onLoadStart: 	function() { captionOff(); activityIndicatorOn(); },
			onLoadEnd:	 	function() { captionOn(); activityIndicatorOff(); $( '.imagelightbox-arrow' ).css( 'display', 'block' ); }
		});
		
		/*****************Image Magnifier***********************/
		
		var options = {height: 190, width: 190};
		$(".zoom_it").each(function() {
		  $(this).image_zoomer(options);
		});
		
	} catch(err){}
	try {
		$('.numericOnly').keypress(function(e) {
		    if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
		               return false;
		    }
		});

		$('.decimalOnly').keypress(function(event) {
			  if ((event.which != 46 || $(this).val().indexOf('.') != -1) &&
			    ((event.which < 48 || event.which > 57) &&
			      (event.which != 0 && event.which != 8))) {
			    event.preventDefault();
			  }
			  var text = $(this).val();
			  if ((text.indexOf('.') != -1) &&
			    (text.substring(text.indexOf('.')).length > 2) &&
			    (event.which != 0 && event.which != 8) &&
			    ($(this)[0].selectionStart >= text.length - 2)) {
			    event.preventDefault();
			  }
			});

		$(".tooltipContent").popover({
		    animation:"fade",
		    delay: "200",
		    trigger:"focus",
		    placement: "top"
		});
		$(".tooltipContentL").popover({
		    animation:"fade",
		    delay: "200",
		    trigger:"focus",
		    placement: "left"
		});
		$(".tooltipContentR").popover({
		    animation:"fade",
		    delay: "200",
		    trigger:"focus",
		    placement: "right"
		});
		
	} catch(err) {console.error(err);}
});

$(document).ajaxComplete(function() {
	$(".tooltipContent").popover({
	    animation:"fade",
	    delay: "200",
	    trigger:"focus",
	    placement: "top"
	});
	$(".tooltipContentL").popover({
	    animation:"fade",
	    delay: "200",
	    trigger:"focus",
	    placement: "left"
	});
	$(".tooltipContentR").popover({
	    animation:"fade",
	    delay: "200",
	    trigger:"focus",
	    placement: "right"
	});
});

$('#fromDate').datepicker({
	todayHighlight: true,
	format: "dd/mm/yyyy"
}).on('changeDate', function(e){
    $(this).datepicker('hide');
});
$('#toDate').datepicker({
	todayHighlight: true,
	format: "dd/mm/yyyy"
}).on('changeDate', function(e){
    $(this).datepicker('hide');
});

/*$("#carddate").datepicker({
	todayHighlight: true,
    viewMode: 'years',
     format: 'mm-yyyy'
     }).on('changeDate', function(e){
    $(this).datepicker('hide');
});*/

$("#carddate").datepicker( {
    format: "mm-yyyy",
    viewMode: "months", 
    minViewMode: "months"
  }).on('changeDate', function(e){
    $(this).datepicker('hide');
});