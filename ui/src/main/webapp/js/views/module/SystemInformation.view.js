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
        'marionette',
        'underscore',
        'jquery',
        'moment',
        'text!systemInformationTemplate',
        'icanhaz',
        'datatables'
    ],
    function(Marionette, _, $, moment, SystemInformationTemplate, ich){
        'use strict';

        if(!ich.systemInformationTemplate) {
            ich.addTemplate('systemInformationTemplate', SystemInformationTemplate);
        }


        var FeaturesView = Marionette.ItemView.extend({
            template: 'systemInformationTemplate',

            serializeData: function() {
                var systemData = this.options.systemInformation.toJSON();
                var operatingSystemData = this.options.operatingSystem.toJSON();
                var uptime = this.getTimeDifference(systemData.Uptime);
                var usedMemory =  operatingSystemData.TotalPhysicalMemorySize - operatingSystemData.FreePhysicalMemorySize;
                var startTime = moment(systemData.StartTime).toDate();

                var returnValue = {
                    systemInformation: systemData,
                    operatingSystem: operatingSystemData,
                    startTime: startTime,
                    uptime: uptime,
                    usedMemory: usedMemory,
                    runtime: systemData.SystemProperties['java.runtime.name'],
                    runtimeVersion: systemData.SystemProperties['java.runtime.version']
                };
                console.log(returnValue);
                return returnValue;
            },

            /**
             * Solution taken from stackoverflow. Link included.
             * http://stackoverflow.com/questions/1787939/check-time-difference-in-javascript
             * @param nTotalDiff
             * @returns {string}
             */
            getTimeDifference: function(nTotalDiff) {

            var oDiff = {};

            oDiff.days = Math.floor(nTotalDiff / 1000 / 60 / 60 / 24);
            nTotalDiff -= oDiff.days * 1000 * 60 * 60 * 24;

            oDiff.hours = Math.floor(nTotalDiff / 1000 / 60 / 60);
            nTotalDiff -= oDiff.hours * 1000 * 60 * 60;

            oDiff.minutes = Math.floor(nTotalDiff / 1000 / 60);
            nTotalDiff -= oDiff.minutes * 1000 * 60;

            oDiff.seconds = Math.floor(nTotalDiff / 1000);
            //  -------------------------------------------------------------------  //

            //  Format Duration
            //  -------------------------------------------------------------------  //
            //  Format Hours
            var hourtext = '00';
            if (oDiff.days > 0){
                hourtext = String(oDiff.days);
            }
            if (hourtext.length === 1) {
                hourtext = '0' + hourtext;
            }

            //  Format Minutes
            var mintext = '00';
            if (oDiff.minutes > 0){
                mintext = String(oDiff.minutes);
            }
            if (mintext.length === 1) {
                mintext = '0' + mintext;
            }

            //  Format Seconds
            var sectext = '00';
            if (oDiff.seconds > 0) {
                sectext = String(oDiff.seconds);
            }
            if (sectext.length === 1) {
                sectext = '0' + sectext;
            }

            //  Set Duration
            var sDuration = hourtext + ':' + mintext + ':' + sectext;
            //  -------------------------------------------------------------------  //

            return sDuration;
        }

        });

        return FeaturesView;
    }
);