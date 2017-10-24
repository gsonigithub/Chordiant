"use strict";

/**
 * @file doit.js
 * @author bishan.kumar.madhoo
 * @since 1.0
 * @version 1.0
 * @description Javascript objects and functions for Do It Task Manager web application
 */
$(document).ready(function () {

    // Hide date time pickers when blurring
    $('body').click(function(e) {

        $('.bootstrap-datetimepicker-widget').each(function() {
            $(this).css('display', 'none');
            $(this).removeClass('picker-open');
        });
    });

    // Clear a selectbox
    $.clearSelectBox = function(combobox, defaultItem, defaultItemValue, disable) {

        // Remove all options form the target combobox
        combobox.html('');

        // Add the default item to the combobox
        combobox.append($('<option>', {
            value: defaultItemValue,
            text: defaultItem
        }));

        // Set the default selected item of the combobox
        combobox.val(defaultItemValue);

        // Update the selectbox
        combobox.selectbox('detach');
        combobox.selectbox('attach');

        // Check if it is required to disable the selectbox
        if (disable) {

            // Disable the selectbox
            combobox.selectbox('disable');
        }
    };

    // Clear errors for the fields of a modal window
    $.clearModalErrorFields = function(modalWindowId) {

        // Clear field error highlights
        $('#' + modalWindowId + ' .form-group').each(function() {
            $(this).removeClass('has-error');
        });
    };

    // Clear the messages being displayed in the specified modal window
    $.clearModalMessages = function(modalWindowId) {

        // Fade out the message container before removing it from the DOM
        $('#' + modalWindowId + ' .modal-body form .doit-modal-messages').remove();
    };

    // Add a message to the specified modal window
    $.addModalMessage = function(modalWindowId, messageType, message, appendToCurrent, dismissable) {

        var hasMessageContainer = false,
            messageContainer,
            modalForm = $('#' + modalWindowId);

        // Default value for appendToCurrent
        if (appendToCurrent === undefined) {
            appendToCurrent = false;
        }

        // Default value for dismissable - can be omitted when calling function
        if (dismissable === undefined) {
            dismissable = false;
        }

        // Check if the modal window contains the messages container
        if ($('.modal-body form .doit-modal-messages', modalForm).length == 0) {
            $('.modal-body form', modalForm).prepend('<div class="doit-modal-messages"></div>')
        }

        // Messages container
        var messagesContainer = $('#' + modalWindowId + ' .modal-body form .doit-modal-messages');

        // Check if messages container already contains a message container for the specified message type
        switch (messageType.toLowerCase()) {

            case 'success':
                messageContainer = messagesContainer.children('.alert-success');
                hasMessageContainer = messageContainer.length > 0;
                break;

            case 'warning':
                messageContainer = messagesContainer.children('.alert-warning');
                hasMessageContainer = messageContainer.length > 0;
                break;

            case 'danger':
                messageContainer = messagesContainer.children('.alert-danger');
                hasMessageContainer = messageContainer.length > 0;
                break;

            default:
                messageContainer = messagesContainer.children('.alert-info');
                hasMessageContainer = messageContainer.length > 0;
                break;
        }

        // Check if we need to create a new message container
        if (!appendToCurrent || !hasMessageContainer) {

            // Message that needs to be displayed
            var alert = $('<div class="alert"></div>');
            alert.html('<ul><li><span>' + message + '</span></li></ul>');

            // Set the message type
            switch (messageType.toLowerCase()) {

                case 'success':
                    alert.addClass('alert-success');
                    break;

                case 'warning':
                    alert.addClass('alert-warning');
                    break;

                case 'danger':
                    alert.addClass('alert-danger');
                    break;

                default:
                    alert.addClass('alert-info');
                    break;
            }

            // Check if message can be dismissed by the user
            if (dismissable) {
                alert.addClass('alert-dismissable');
                alert.prepend('<button class="close" aria-hidden="true" data-dismiss="alert" type="button">×</button>');
            }

            // Append message to modal window message container
            messagesContainer.append($(alert));
        }
        else {

            // Add the message to the appropriate container
            messageContainer.children('ul').append('<li><span>' + message + '</span></li>');
        }
    };

    // Save modal form data
    $.performSave = function(formId, modalWindowId, postUri, failMessage, successCallback, showErrorMessages,
                             closeAfterSuccess) {

        // Modal activity indicator
        var modalActivityIndicator = $('#' + modalWindowId + ' #modal-form-activity');

        // Modal header close button
        var modalHeaderCloseButton = $('#' + modalWindowId + ' .modal-header .close');

        // Function to asynchronously save form data
        var saveData = function() {

            // Save form data
            $.ajax({
                async: false,
                cache: false,
                complete: function() {

                    // Enable all modal window footer buttons
                    $('#' + modalWindowId + ' .modal-footer button').each(function() {
                        $(this).removeAttr('disabled');
                    });

                    // Fade in the modal header close button if it exists
                    if (modalHeaderCloseButton.length > 0) {
                        modalHeaderCloseButton.fadeIn('fast');
                    }

                    // Fade out the modal activity indicator
                    if (modalActivityIndicator.length > 0) {
                        modalActivityIndicator.fadeOut('fast');
                    }
                },
                data : $('#' + formId).serialize(),
                dataType: 'json',
                error: function() {

                    // An error occurred while connecting to the specified URL and carrying out the specified operation
                    $.addModalMessage(modalWindowId, 'danger', failMessage);
                },
                success : function(data) {

                    // Check if new task was successfully saved
                    if (!data.hasError) {

                        // Check if we need to close the modal window
                        if (closeAfterSuccess) {
                            $('#' + modalWindowId).modal('hide');
                        }
                        
                        // Check if a success callback function has been defined
                        if (successCallback !== undefined) {
                            successCallback();
                        }
                    }
                    else {

                        // New task was not successfully saved so display error messages
                        for (var i = 0; i < data.errorMessages.length; i++) {

                            // Display error message
                            $.addModalMessage(modalWindowId, 'danger', data.errorMessages[i].value, true, true);

                            // Highlight error fields
                            var field = $('#' + modalWindowId + ' #' + data.errorMessages[i].key);
                            var fieldContainer = field.parentsUntil('.form-group').last().parent();
                            fieldContainer.addClass('has-error');
                        }
                    }
                },
                type: 'POST',
                url: postUri
            });
        };

        // Clear messages being displayed in the modal form
        $.clearModalMessages(modalWindowId);

        // Clear modal form error field highlights
        $.clearModalErrorFields(modalWindowId);

        // Disable all modal window footer buttons
        $('#' + modalWindowId + ' .modal-footer button').each(function() {
            $(this).attr('disabled', 'disabled');
        });

        // Check if modal window has an activity indicator
        if (modalActivityIndicator.length > 0) {

            // Fade out the modal header close button if it exists
            if (modalHeaderCloseButton.length > 0) {
                modalHeaderCloseButton.fadeOut('fast');
            }

            // Fade in the modal activity indicator
            modalActivityIndicator.fadeIn('fast', function() {

                // Save form data
                saveData();
            });
        }
        else if (modalHeaderCloseButton.length > 0) {

            // Fade out the modal header close button
            modalHeaderCloseButton.fadeOut('fast', function() {

                // Save form data
                saveData();
            });
        }
        else {

            // Save form data
            saveData();
        }
    };

    // Update the title of a label in local cache
    $.updateLocalCacheLabelTitle = function(id, title, labels) {

        // Check that the array of labels has been defined
        if (labels != null) {

            // Traverse the array of labels
            for (var i = 0; i < labels.length; i++) {

                // Check if we are at the required label ID
                if (labels[i].labelId == id) {

                    // Update the title of the label
                    labels[i].title = title;
                    break;
                }
            }
        }
    };

    // Generate the content of the labels popover
    $.generateLabelsPopoverContent = function(labels, allowLabelsUpdate, taggedLabels) {

        // Check if labels has been defined
        if (labels === undefined) {
            labels = null;
        }

        // Check if allowLabelsUpdate has been defined
        if (allowLabelsUpdate === undefined) {
            allowLabelsUpdate = false;
        }

        // Labels popover content
        var content = '';

        // Check if the labels array has been declared
        if (labels != null) {

            // Array of selected label ID
            var selectedLabelIds = taggedLabels.val().trim().split(',');

            // Start the popover body container
            content = '<div id="labels-body" class="scrollable-popover-body">';

            // Start label container
            content += '<ul class="edit-labels">';

            // Traverse the array of labels with which the current task can be tagged
            for (var i = 0; i < labels.length; i++) {

                // Add the current label to the content
                content += '<li>';
                content += '<a class="task-label';

                // Check if the label has already been selected
                if (selectedLabelIds.indexOf(labels[i].labelId.toString()) < 0) {
                    content += ' selectable';
                }

                content += '" style="background-color:' + labels[i].colour + ';" data-content="'
                    + labels[i].labelId + '">';

                if (labels[i].title != null) {
                    content += '<span class="label-name">' + labels[i].title.trim() + '</span>';
                }
                else {
                    content += '<span class="label-name"></span>';
                }

                content += '<span class="fa fa-check"></span>';
                content += '</a>';
                content += '</li>';
            }

            // Close label container
            content += '</ul>';

            // Check if user is allowed to update the title of labels
            if (allowLabelsUpdate) {

                // Start the label title editor container
                content += '<ul class="label-editors">';

                // Traverse the array of labels with which the current task can be tagged
                for (var j = 0; j < labels.length; j++) {

                    // Start the editor for the current label
                    content += '<li>';

                    // Label colour
                    content += '<span class="label-editor-hint" style="background-color: ' +
                        labels[j].colour + ';"></span>';

                    // Label editor
                    content += '<input class="label-title-editor" placeholder="Title of label" value="';

                    if (labels[j].title != null) {
                        content += labels[j].title.trim();
                    }

                    content += '" role="' + labels[j].labelId + '" />';

                    // Close the editor for the current label
                    content += '</li>';
                }

                // Close the label title editor container
                content += '</ul>';
            }

            // Close the popover body container
            content += '</div>';
        }

        // Check if user is allowed to update the title of the labels
        if (allowLabelsUpdate) {

            content += '<div class="popover-footer">'
                + '<a id="show-labels-editor" class="popover-edit-label-link" role="edit-labels">'
                + 'Edit the title of the labels</a></div>';
        }

        return content;
    };

    // Task label handler
    $.taskLabelHandler = function(target, labelsContainer, taggedLabels) {

        // Select or deselect the label
        target.toggleClass('selectable');

        // Check if label was tagged or untagged
        if (target.hasClass('selectable')) {

            // Label was untagged, so remove it from the list of tagged labels
            labelsContainer.children('[data-content="' + target.attr('data-content')+ '"]').remove();
        }
        else {

            // Create tagged label
            var taggedLabel = $('<div class="taggedLabel"></div>');

            // Set the properties of the tagged label
            taggedLabel.html(target.children('.label-name').html());
            taggedLabel.css('background-color', target.css('background-color'));
            taggedLabel.attr('data-content', target.attr('data-content'));

            // Label was tagged, so add it to the list of tagged labels
            labelsContainer.children('#addTaskLabel').before(taggedLabel);
        }

        // Array of selected label ID
        var selectedLabels = [];

        // Traverse the list of labels
        $('.task-label', labelsContainer).each(function() {

            // Check if user has selected the current label
            if (!$(this).hasClass('selectable')) {

                // Add the ID of the label to the array of selected labels
                selectedLabels.push(parseInt($(this).attr('data-content'), 10));
            }
        });

        // Set the list of selected labels
        taggedLabels.val(selectedLabels.join(','));
    };

    // Show label editor
    $.labelEditorHandler = function(labelsEditorPopover, labelsContainer, labels) {

        var button = $('#show-labels-editor', labelsEditorPopover),
            labelsBody = $('#labels-body', labelsEditorPopover),
            labelEditors = $('.label-editors', labelsEditorPopover),
            labelSelectors = $('.edit-labels', labelsEditorPopover);

        // Check if we need to show the labels' editor
        if (button.attr('role') == 'edit-labels') {

            // Show the labels' editor
            labelsBody.css('left', '-300px');
            labelEditors.css('opacity', 1);
            labelSelectors.css('opacity', 0);

            // Set the properties of the link
            button.html('Update titles');
            button.attr('role', 'save-labels');
        }
        else {

            // Array of label title updates
            var updatesArray = [];

            // Construct the array of title updates
            $('.label-title-editor', labelsEditorPopover).each(function() {

                // Current update details
                var currentUpdate = {
                    id : parseInt($(this).attr('role')),
                    title : null
                };

                // Check if a title was provided
                if ($(this).val().trim().length > 0) {
                    currentUpdate.title = $(this).val().trim();
                }

                // Update all labels
                if (currentUpdate.title == null) {

                    $('.taggedLabel[data-content="' + currentUpdate.id + '"]', labelsContainer).html('');
                    $('.task-label[data-content="' + currentUpdate.id + '"] .label-name', labelsContainer).html('');
                }
                else {

                    $('.taggedLabel[data-content="' + currentUpdate.id + '"]', labelsContainer).html(currentUpdate.title);
                    $('.task-label[data-content="' + currentUpdate.id + '"] .label-name', labelsContainer).html(currentUpdate.title);
                }

                // Update the title of the label in the local cache
                $.updateLocalCacheLabelTitle(currentUpdate.id, currentUpdate.title, labels);

                // Add the update to the array
                updatesArray.push(currentUpdate);
            });

            // Hide the labels' editor
            labelsBody.css('left', '0');
            labelEditors.css('opacity', 0);
            labelSelectors.css('opacity', 1);

            // Set the role of the link
            button.html('Edit the title of the labels');
            button.attr('role', 'edit-labels');
        }
    };
});

//function drawing chart
var drawChartPie = function(container, dataViews, width, height,type,baseLink){
	
	var data = $.parseJSON(dataViews.replace("\\", ""));

	var SEGMENT = "status";
	var DATA = "count";
	var LABEL = "labelstatus";

	var radius = Math.min(width, height) / 3;

	var color = d3.scale.category20();

	var arc = d3.svg.arc()
	    .outerRadius(radius - 10)
	    .innerRadius(radius - 70);

	var pie = d3.layout.pie()
	    .sort(null)
	    .value(function(d) { return d[DATA]; });

	var svg = d3.select(container).append("svg")
	    .attr("width", width)
	    .attr("height", height)
	  .append("g")
	    .attr("transform", "translate(" + width / 4 + "," + height / 2 + ")");

	  data.forEach(function(d) {
	    d[DATA] = +d[DATA];
	  });

	  var g = svg.selectAll(".arc")
	      .data(pie(data))
	    .enter().append("g")
	      .attr("class", "arc");

	  var count = 0;

	  g.append("path")
	      .attr("d", arc)
	      .attr("id", function(d) { return "arc" + type + "-" + (count++); })
	      .style("fill", function(d) { return color(d.data[SEGMENT]); })
	      .style("cursor","hand")
	      .on("click", function(d){
	    	  $(location).attr('href',baseLink + "/status/" + d.data[SEGMENT]);
	      });
	  		

	  g.append("text")
	      .attr("transform", function(d) { return "translate(" + arc.centroid(d) + ")"; })
	      .attr("dy", ".35em")
	      .style("text-anchor", "middle")
	      .text(function(d) { return d.data[DATA]; });

	  count = 0;
	  var legend = svg.selectAll(".legend")
	      .data(data).enter()
	      .append("g").attr("class", "legend")
	      .attr("legend-id", function(d) {
	          return count++;
	      })
	      .attr("transform", function(d, i) {
	          return "translate(-50," + (-70 + i * 20) + ")";
	      })
	      .on("click", function() {
	          var arc = d3.select("#arc" + type + "-" + $(this).attr("legend-id"));
	          arc.style("opacity", 0.3);
	          setTimeout(function() {
	              arc.style("opacity", 1);
	          }, 1000);
	      });

	  legend.append("rect")
	      .attr("x", width / 2)
	      .attr("width", 18).attr("height", 18)
	      .style("fill", function(d) {
	          return color(d[SEGMENT]);
	      })
	      .style("cursor","hand");
	  legend.append("text").attr("x", width / 2)
	      .attr("y", 9).attr("dy", ".35em")
	      .style("text-anchor", "end").text(function(d) {
	          return d[LABEL];
	      });
	
};


/**
 * Function to draw bar chart
 * @author avishekh.khedan
 */
function drawBarChart(){
	var COUNT = "count";
    var barWidth = 20;
    var margin = {
        top: 50,
        right: 5,
        bottom: 5,
        left: 5,
        labels: 5
    };
    var height = 200; //overridden by width in call
    var width = 200; //overridden by width in call    
    var chartTitle = ["test"];
    var xformat = function(d){return d;};
    var focus;
    
    function chart(selection) {
        var maxBarHeight = height - (margin.top + margin.bottom);
        var chartWidth = width - margin.right - margin.left;
        
        //d3.select('svg').remove();//remove old charts

        selection.each(function(data) {
            
            var xValue = function(d) { return d[COUNT]; };
            var yValue = function(d) { return d[COUNT]; };

            var x = d3.scale.ordinal()
                .domain(data.map(function(d) { return d[COUNT]; }))
                 .rangeRoundBands([margin.labels, chartWidth], 0);
            
            var y = d3.scale.linear()
                .domain([0, d3.max(data, function(d) { return d[COUNT]; })])
                .range([maxBarHeight, 0])
                .nice();
            
  
        
            //var yAxis = d3.svg.axis().scale(y).orient("left");
        
            var svgContainer = d3.select(this).append("svg")
                .attr("class", "chart mini-column-chart")
                .attr("width", width)
                .attr("height", height).append("g")
                .attr("transform", "translate(" + margin.left + "," + margin.top + ")");
        
            svgContainer.append("g")
                .attr("class", "x axis")
                .attr("transform", "translate( 0," + (height - margin.top - margin.bottom) + ")")
                
                
                .append("text")
                .attr("class", "axis-label")
                .attr("x", width/2)
                .attr("text-anchor", "middle")
                .attr("dy", 48)

            var header = svgContainer.append("text")
                .attr("class", "chart-title")
                .attr("x", width/2)
                .attr("text-anchor", "middle")
                .attr("dy", -32)
                .text(chartTitle);
            
            var barValues = svgContainer.append("g")
                .attr("class", "bar-values");
            
            barValues.selectAll("text")
                .data(data)
                    .enter().append("text")
                .attr("x", function(d, i) {
                    return ((i+1) * x.rangeBand());
                })
                .attr("y", function(d) { return y(d[COUNT]);})
                .attr("dy", -5)
                .attr("text-anchor", "middle")
                .text(function(d) {return d[COUNT];});

            svgContainer.append("g")
                .attr("class", "y axis")//.call(yAxis)
                .append("text")
                .attr("class", "axis-label")
                .attr("transform", "rotate(-90)")
                .attr("y", 8)
                .attr("x", -(height-margin.top-margin.bottom))
                //.attr("dy", ".71em")
                .style("text-anchor", "start")
        
            var bars = svgContainer.append("g")
                .attr("class", "bars");
                
            bars.selectAll(".bar")
                .data(data)
                    .enter().append("rect")
                .attr("class", "bar")
                .attr("x", function(d, i) {
                    return ((i+1) * x.rangeBand())-(barWidth);
                })
                .attr("y", function(d) { return y(d[COUNT]);})
                .attr("width", barWidth)
                .attr("height", function(d) {
                    return (maxBarHeight -y(d[COUNT]));
                });
        });
    }


    chart.title = function(_) {
        if (!arguments.length) return chartTitle;
        chartTitle = _;
        return chart;
    };

    chart.x = function(_) {
        if (!arguments.length) return xValue;
        xValue = _;
        return chart;
    };

    chart.y = function(_) {
        if (!arguments.length) return yValue;
        yValue = _;
        return chart;
    };

    chart.width = function(_) {
        if (!arguments.length) return width;
        width = _;
        return chart;
    };

    chart.height = function(_) {
        if (!arguments.length) return height;
        height = _;
        return chart;
    };

    chart.barWidth = function(_) {
        if (!arguments.length) return barWidth;
        barWidth = _;
        return chart;
    };
    
    chart.xformat = function(_) {
        if (!arguments.length) return xformat;
        xformat = _;
        return chart;
    };
    
    chart.yAxisLabel = function(_) {
        if (!arguments.length) return yAxisLabel;
        yAxisLabel = _;
        return chart;
    };

    chart.xAxisLabel = function(_) {
        if (!arguments.length) return xAxisLabel;
        xAxisLabel = _;
        return chart;
    };

    return chart;
};