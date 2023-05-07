$().ready(function () {
   // Validate patient form
    $("#patientForm").validate({
        rules: {
            quantity96116: {
                digits: true
            },
            quantity96121: {
                digits: true
            },
            quantity96132: {
                digits: true
            },

            quantity96133: {
                digits: true
            },
        }
    })
});