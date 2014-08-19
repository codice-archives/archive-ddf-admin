/**
 * Copyright (c) Codice Foundation
 *
 * This is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser
 * General Public License as published by the Free Software Foundation, either version 3 of the
 * License, or any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details. A copy of the GNU Lesser General Public License
 * is distributed along with this program and can be found at
 * <http://www.gnu.org/licenses/lgpl.html>.
 *
 **/
/*global define*/
define([
    'backbone',
    'marionette',
    'icanhaz',
    'js/application',
    'text!applicationDetailLayout',
    '/applications/js/controller/Feature.controller.js'
], function(Backbone, Marionette, ich, Application, applicationDetailLayout, FeatureController) {
    "use strict";

    ich.addTemplate('applicationDetailLayout', applicationDetailLayout);

    var App = Application.App;

    var DetailedApplicationLayout = Marionette.Layout.extend({
        template: 'applicationDetailLayout',
        regions: {
            content: '.content',
            features: '#features'
        },
        initialize: function(options){
            this.model = options.model;
            this.appKey = this.model.get('name') + "-" + this.model.get('version');
            console.log("Current Application: " + this.appKey);
        },
        events: {
            'click .nav-to-applications': 'navToApplications',
            'click #featureTab': 'getFeatures'
        },
        onRender: function(){
            console.log(App.module('Applications'));  // use this object to get reference to module.controllers
        },
        navToApplications: function(e){
            e.preventDefault();
            App.vent.trigger('navigateTo:applicationHome');
        },
        getFeatures: function(){
            var featureController = new FeatureController({
                region: this.features
            });
            featureController.show(this.appKey);
        }
    });

    return DetailedApplicationLayout;

});
